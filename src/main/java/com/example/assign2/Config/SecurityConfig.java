/* 
 * Assignment 2 - Web Engineering
 * John Villegas (c3476534)
 * Paul Cabanas (c3481070)
 */

package com.example.assign2.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    
// Configures the security filter chain for the application //
// This method sets up the security policies for HTTP requests, including session management, CSRF protection, and authorisation rules. //
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .requiresChannel(channel -> channel.anyRequest().requiresSecure())
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // .requestMatchers("/unix").permitAll()
                        .requestMatchers("/addStudent.html").permitAll()
                        .requestMatchers("/loginStudent.html").permitAll()
                        .requestMatchers("/selectSemester.html").permitAll()
                        .requestMatchers("/selectCourses.html").permitAll()
                        .requestMatchers("/enrolment.html").permitAll()
                        .requestMatchers(HttpMethod.POST, "/unix/student/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/unix/student/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/unix/enrol").permitAll()
                        .requestMatchers(HttpMethod.GET, "/unix/enrol").permitAll()
                        .requestMatchers(HttpMethod.GET, "/unix/semesters/GetCourses").permitAll()
                        .requestMatchers(HttpMethod.GET, "/unix/student/logout").permitAll()
                        .requestMatchers(HttpMethod.GET, "/unix/semesters").permitAll()
                        .anyRequest().authenticated());
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

}
