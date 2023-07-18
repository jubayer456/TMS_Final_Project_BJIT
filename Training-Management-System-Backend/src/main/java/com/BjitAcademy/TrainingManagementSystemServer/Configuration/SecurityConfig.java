package com.BjitAcademy.TrainingManagementSystemServer.Configuration;

import com.BjitAcademy.TrainingManagementSystemServer.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/admin","/api/admin/AllUser")
                .permitAll()
                .requestMatchers("/api/auth/login")
                .permitAll() .requestMatchers("/api/auth/trainee","/api/trainee/{traineeId}","/api/trainee/getAll")
                .permitAll().requestMatchers("/api/auth/trainer","/api/auth/trainer","/api/trainer/{trainerId}","/api/trainer/getAll")
                .permitAll().requestMatchers("/api/course/save","/api/course/{courseId}","/api/course/getAll","/api/course/{courseId}")
                .permitAll()
                .requestMatchers("/api/batch/save","/api/batch/{batchId}","/api/batch/getAll","/api/batch/add-trainee","/api/batch/remove-trainee/{traineeId}",
                        "/api/batch/add-schedule","/api/batch/remove-schedule/{scheduleId}","/api/batch/{batchId}/getAllSchedule","/api/batch/{batchId}/getAllTrainee"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }
}
