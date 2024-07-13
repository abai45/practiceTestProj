package kz.testProject.testingSecurityMethods.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping(value = "/signIn")
    public String signIn() {
        return "signIn";
    }
}
