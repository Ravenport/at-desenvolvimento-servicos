package infnet.ds.tp3.sistemainterno.configuration;

import infnet.ds.tp3.sistemainterno.repositories.TeacherRepository;
import infnet.ds.tp3.sistemainterno.services.Auth.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/teacher/register").permitAll()
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/teacher/**").hasRole("TEACHER")
                .requestMatchers("/grade/**").hasRole("TEACHER")
                .requestMatchers("/student/**").hasRole("TEACHER")
                .requestMatchers("/school/classes/**").hasRole("TEACHER")
        ).csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) throws Exception {
        AuthenticationManagerBuilder authManager = http.getSharedObject(AuthenticationManagerBuilder.class);

        authManager.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

        return authManager.build();
    }

    @Bean
    public UserDetailsService userDetailsService(TeacherRepository teacherRepository) {
        return new CustomUserDetailService(teacherRepository);
    }
}
