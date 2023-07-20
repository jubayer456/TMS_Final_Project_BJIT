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
                .requestMatchers("/api/admin","/api/admin/AllUser","/api/admin/{adminId}")
                .permitAll().requestMatchers("/api/trainee","/api/trainee/getAll","/api/trainee/{traineeId}")
                .permitAll().requestMatchers("/api/trainer","/api/trainer/getAll","/api/trainer/{trainerId}")
                .permitAll()
                .requestMatchers("/api/auth/login","/api/auth/changePass/{userId}","/api/auth/updatePicture/{userId}","/api/auth/{userId}")
                .permitAll() .requestMatchers("/api/auth/trainee","/api/trainee/{traineeId}","/api/trainee/getAll")
                .permitAll().requestMatchers("/api/course/save","/api/course/{courseId}","/api/course/getAll","/api/course/{courseId}")
                .permitAll()
                .requestMatchers("/api/batch/save","/api/batch/{batchId}","/api/batch/getAll","/api/batch/add-trainee","/api/batch/remove-trainee/{traineeId}",
                        "/api/batch/add-schedule","/api/batch/remove-schedule/{scheduleId}","/api/batch/{batchId}/getAllSchedule","/api/batch/{batchId}/getAllTrainee"
                )
                .permitAll().requestMatchers("/api/schedule/{scheduleId}/add-assignment","/api/schedule/{trainerId}","/api/schedule/{assignmentId}","/api/schedule/{assignmentId}","/api/schedule/add-assignmentSub","/api/schedule/{scheduleId}/allAssignment",
                        "/api/schedule/{scheduleId}/{assignmentId}","/api/schedule/{batchId}/allAssignmentSub","/api/schedule/{assignmentId}/{submissionId}")
                .permitAll().requestMatchers("/api/upload","/api/download/{fileName}")
                .permitAll().requestMatchers("/api/classroom/{trainerId}/getAllClassRoom","/api/classroom/{classId}/getAllPost","/api/classroom/remove-comment/{postId}/{commentId}",
                        "/api/classroom/remove-post/{postId}","/api/classroom/update-post/{postId}","/api/classroom/update-comment/{commentId}",
                        "/api/classroom/add-comment","/api/classroom/{classId}/getAllNotice","/api/classroom/add-notice","/api/classroom/add-post")
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
