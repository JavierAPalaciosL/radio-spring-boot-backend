package io.radioweather.radioweatherapi.infrastructure.security;

import io.radioweather.radioweatherapi.infrastructure.filters.CorsFilter;
import io.radioweather.radioweatherapi.infrastructure.filters.InterceptorToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class Security {

    @Bean
    public SecurityFilterChain securityPuta(HttpSecurity http) throws Exception {
        return http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests(httpSecurityAuthorizeHttpRequestsConfigurer -> {
                    httpSecurityAuthorizeHttpRequestsConfigurer
                            .requestMatchers("/v1/countries/**").permitAll()
                            .requestMatchers("/v1/regions/**").permitAll().
                            requestMatchers("/v1/weather/**").permitAll().
                            requestMatchers("/v1/users/login").permitAll().
                            requestMatchers("/v1/users/register").permitAll().
                            requestMatchers("v1/users/oauth").permitAll()
                            .anyRequest().authenticated();
                })
                .addFilterAfter(new InterceptorToken(),  org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new CorsFilter(), InterceptorToken.class)
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable())
                .build();
    }
}
