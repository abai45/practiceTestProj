package kz.testProject.testingSecurityMethods.config;

import kz.testProject.testingSecurityMethods.repositories.UserRepository;
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

        http.formLogin(
                formLogin -> formLogin
                        .loginPage("/signIn")
                        .defaultSuccessUrl("/profile")
                        .failureUrl("/signIn?error")
                        .usernameParameter("email")
                        .passwordParameter("password")
        ).logout(
                logout -> logout
                        .logoutSuccessUrl("/signOut?logout")
                        .logoutUrl("/signOut")
        ).csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
