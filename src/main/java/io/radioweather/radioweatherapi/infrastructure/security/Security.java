package io.radioweather.radioweatherapi.infrastructure.security;

import io.radioweather.radioweatherapi.infrastructure.filters.CorsFilter;
import io.radioweather.radioweatherapi.infrastructure.filters.InterceptorToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class Security {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/v1/countries/**",
                        "/v1/regions/**",
                        "/v1/weather/**",
                        "/v1/users/login",
                        "/v1/users/register",
                        "/v1/users/oauth"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(new InterceptorToken(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new CorsFilter(), InterceptorToken.class)
                .formLogin().disable();

        return http.build();
    }
}
