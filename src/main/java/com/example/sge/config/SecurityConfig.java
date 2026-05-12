package com.example.sge.config;

import com.example.sge.security.JwtAuthFilter;
import com.example.sge.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        private JwtAuthFilter jwtAuthFilter;
        @Autowired private UserDetailsServiceImpl userDetailsService;

    @Bean
    @Order(2)
    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/filieres/**").hasRole("ADMIN")
                        .requestMatchers("/modules/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/notes/**", "/bulletins/**")
                        .hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/notes/**").hasRole("ADMIN")
                        .requestMatchers("/etudiants/**")
                        .hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    @Order(1)
    public SecurityFilterChain bulletinSecurity(HttpSecurity http) throws Exception {

        http
                .securityMatcher("/bulletins/**", "/login", "/logout")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/logout").permitAll()
                        .anyRequest().hasAnyRole("ADMIN", "USER")
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/bulletins", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                );

        return http.build();
    }
        @Bean
        public AuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider p = new DaoAuthenticationProvider(userDetailsService);
            p.setPasswordEncoder(passwordEncoder());
            return p;
        }

        @Bean
        public AuthenticationManager authenticationManager(
                AuthenticationConfiguration c) throws Exception {
            return c.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
}