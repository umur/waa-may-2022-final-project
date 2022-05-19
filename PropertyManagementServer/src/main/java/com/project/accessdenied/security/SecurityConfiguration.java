package com.project.accessdenied.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService awesomeUserDetailsService;
    private final JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(awesomeUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/*").permitAll()
                .antMatchers("/api/v1/cities/").permitAll()
                .antMatchers("/api/v1/cities/*").permitAll()
                .antMatchers("/api/v1/states/").permitAll()
                .antMatchers("/api/v1/states/*").permitAll()
                .antMatchers("/api/v1/property-types/").permitAll()
                .antMatchers("/api/v1/properties/*").hasAnyAuthority("TENANT", "ADMIN", "LANDLORD")
                .antMatchers("/api/v1/properties/").hasAnyAuthority("TENANT", "ADMIN", "LANDLORD")
                .antMatchers("/payment").hasAuthority("TENANT")
                .antMatchers("/api/v1/users/*").hasAnyAuthority("TENANT", "ADMIN", "LANDLORD")
                .antMatchers("/api/v1/users/").hasAnyAuthority("TENANT", "ADMIN", "LANDLORD")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
