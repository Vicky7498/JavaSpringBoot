package com.infy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user = User.withUsername("vicky").password(passwordEncoder().encode("vicky")).roles("ADMIN").build();
        UserDetails admin = User.withUsername("pekhale").password(passwordEncoder().encode("pekhale")).roles("USER").build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        String requestMatcher = "/infybank/customers/**";
        httpSecurity.httpBasic(Customizer.withDefaults()).authorizeHttpRequests((authorize) -> authorize.requestMatchers(HttpMethod.GET, requestMatcher).permitAll().requestMatchers(HttpMethod.PUT, requestMatcher).hasAnyRole("ADMIN", "USER").requestMatchers(HttpMethod.POST, requestMatcher).hasRole("ADMIN").requestMatchers(HttpMethod.DELETE, requestMatcher).hasRole("ADMIN").anyRequest().authenticated());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
            corsConfiguration.addAllowedMethod(HttpMethod.PUT);
            corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
            return corsConfiguration;
        }));
        return httpSecurity.build();
    }
}
