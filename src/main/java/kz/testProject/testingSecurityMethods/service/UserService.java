package kz.testProject.testingSecurityMethods.service;

import kz.testProject.testingSecurityMethods.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void createUser(String fullname, String email, String password);
    void updateUser(String fullname, String email, String password, String rePassword);
    List<UserEntity> getUsers();
    void deleteUser(Long id);
}
