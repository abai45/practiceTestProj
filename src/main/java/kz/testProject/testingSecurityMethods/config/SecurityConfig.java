package kz.testProject.testingSecurityMethods.config;

import kz.testProject.testingSecurityMethods.service.UserService;
import kz.testProject.testingSecurityMethods.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService()).passwordEncoder(passwordEncoder());

        http.authorizeHttpRequests(
                authorizeRequests -> authorizeRequests
                        .requestMatchers(GET, "/", "/signIn", "/signUp").permitAll()
                        .requestMatchers(POST, "/signUp").permitAll()
                        .requestMatchers(POST, "/signIn").anonymous()
                        .requestMatchers(GET, "/profile").authenticated()
                        .anyRequest().permitAll()
        ).formLogin(
                formLogin -> formLogin
                        .loginProcessingUrl("/enter")
                        .loginPage("/signIn")
                        .defaultSuccessUrl("/profile")
                        .failureUrl("/signIn?error")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .permitAll()
        ).logout(
                logout -> logout
                        .logoutUrl("/signOut")
                        .logoutSuccessUrl("/")
                        .permitAll()
        ).csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
