package kz.testProject.testingSecurityMethods.service.impl;

import kz.testProject.testingSecurityMethods.entities.PermissionEntity;
import kz.testProject.testingSecurityMethods.entities.UserEntity;
import kz.testProject.testingSecurityMethods.repositories.PermissionRepository;
import kz.testProject.testingSecurityMethods.repositories.UserRepository;
import kz.testProject.testingSecurityMethods.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    public void createUser(String fullname, String email, String password) {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setFullName(fullname);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        List<PermissionEntity> permissions = new ArrayList<>();
        PermissionEntity permissionEntity = permissionRepository.findByName("USER");
        permissions.add(permissionEntity);
        user.setPermissions(permissions);
        userRepository.save(user);
    }

    @Override
    public void updateUser(String fullname, String email, String password, String rePassword) {
        if(userRepository.findByEmail(email).isPresent()) {
            if(!passwordEncoder.matches(password, rePassword)) {
                throw new RuntimeException("Wrong password");
            }
            UserEntity user = userRepository.findByEmail(email).get();
            user.setFullName(fullname);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
        } else {
            throw new RuntimeException("User does not exist");
        }
    }

    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
