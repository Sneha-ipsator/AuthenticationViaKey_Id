package com.sneha.usersignupsignin.security;

import com.sneha.usersignupsignin.entity.User;
import com.sneha.usersignupsignin.payload.IsValidResponse;
import com.sneha.usersignupsignin.repository.UserRepository;
import com.sneha.usersignupsignin.service.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Lazy
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestUri = request.getRequestURI();

        if(requestUri.startsWith("/public/")){
            filterChain.doFilter(request, response);
        }

        else{
            String userLoginId = request.getHeader("X-User-Login-Id");
            String userLoginKey = request.getHeader("X-User-Login-Key");
            System.out.println("Key : " +userLoginKey );
            System.out.println("ID : " +userLoginId );

            System.out.println(userLoginId.isEmpty());
            System.out.println(userLoginKey.isEmpty());
            this.doAuthenticate(userLoginId,userLoginKey);
            if (!userLoginId.isEmpty() && !userLoginKey.isEmpty()) {
                IsValidResponse isValidResponse = userServiceImpl.isValidUser(userLoginId, userLoginKey);

                if (isValidResponse.isSuccess()) {
                    System.out.println("Success");
                    User userDetails = userServiceImpl.getUserByLoginId(userLoginId);
                    System.out.println(userDetails);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            }

            filterChain.doFilter(request, response);
        }



    }

    private void doAuthenticate(String userLoginId, String userLoginKey) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userLoginId,userLoginKey);
        try {
            manager.authenticate(authentication);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

}

