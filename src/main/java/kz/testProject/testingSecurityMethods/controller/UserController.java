//package kz.testProject.testingSecurityMethods.controller;
//
//import kz.testProject.testingSecurityMethods.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//@RequiredArgsConstructor
//public class UserController {
//    private final UserService userService;
//
//    @GetMapping("/signIn")
//    @PreAuthorize("isAnonymous()")
//    public String signIn() {
//        return "signIn";
//    }
//
//    @GetMapping("/signUp")
//    public String signUp() {
//        return "signUp";
//    }
//
//    @GetMapping("/")
//    public String home() {
//        return "home";
//    }
//
//    @PostMapping("/signUp")
//    public String signUp(@RequestParam(name = "fullname") String fullname,
//                         @RequestParam(name = "email") String email,
//                         @RequestParam(name = "password") String password) {
//        userService.createUser(fullname, email, password);
//        return "redirect:/signIn";
//    }
//
//    @GetMapping("/profile")
//    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
//    public String profile(Model model, Authentication authentication) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        model.addAttribute("fullName", userDetails.getUsername());
//        return "profile";
//    }
//
//
//    @GetMapping(value = "/settings")
//    @PreAuthorize("isAuthenticated()")
//    public String settings(){
//        return "settings";
//    }
//
//    @PostMapping(value = "/update")
//    @PreAuthorize("isAuthenticated()")
//    public String update(@RequestParam(name = "fullname") String fullname,
//                         @RequestParam(name = "email_user") String email,
//                         @RequestParam(name = "password_user") String password,
//                         @RequestParam(name = "repeat_password") String re_password){
//        userService.updateUser(fullname, password, email, re_password);
//        return "redirect:/profile";
//    }
//
//    @GetMapping(value = "/adminProfile")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String adminProfile(Model model){
//        model.addAttribute("users", userService.getUsers());
//        return "adminProfile";
//    }
//
//    @PostMapping(value = "/deleteUser")
//    public String deleteUser(@RequestParam(name = "id") Long id){
//        userService.deleteUser(id);
//        return "redirect:/adminProfile";
//    }
//}
