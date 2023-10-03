package com.sneha.usersignupsignin.config;

import com.sneha.usersignupsignin.security.CustomAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for defining security-related beans and configurations.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private CustomAuthenticationFilter customAuthenticationFilter;

    /**
     * Configure the security filter chain.
     *
     * @param http The HttpSecurity object to configure.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs while configuring the filter chain.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //configuration
        http.csrf(csrf -> csrf.disable());
        http.cors(cors -> cors.disable());
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/public/saveUser").permitAll().anyRequest().authenticated();
        });
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint((request, response, authException) -> {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: " + authException.getMessage());
                })
        );
        return http.build();

    }

    /**
     * Create a DaoAuthenticationProvider bean for authentication.
     *
     * @return An instance of DaoAuthenticationProvider.
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

    /**
     * Create an AuthenticationManager bean for handling authentication.
     *
     * @param builder The AuthenticationConfiguration builder.
     * @return An instance of AuthenticationManager.
     * @throws Exception If an error occurs while creating the authentication manager.
     */

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

}
