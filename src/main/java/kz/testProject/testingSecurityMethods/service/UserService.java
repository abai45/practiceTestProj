package kz.testProject.testingSecurityMethods.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void createUser(String fullname, String email, String password);
}
