package kz.testProject.testingSecurityMethods.controller;

import kz.testProject.testingSecurityMethods.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/signIn")
    public String signIn() {
        return "signIn";
    }
    @GetMapping("/signUp")
    public String signUp() {
        return "signUp";
    }
    @PostMapping("/signUp")
    public String signUp(@RequestParam(name = "fullname") String fullname,
                         @RequestParam(name = "email") String email,
                         @RequestParam(name = "password") String password) {
        userService.createUser(fullname, email, password);
        return "redirect:/";
    }
    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String profile() {
        return "profile";
    }
}
