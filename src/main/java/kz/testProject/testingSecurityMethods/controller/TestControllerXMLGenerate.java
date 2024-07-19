package kz.testProject.testingSecurityMethods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v2")
public class TestControllerXMLGenerate {
    @Autowired
    SpringTemplateEngine templateEngine;

    @GetMapping(value = "/test", produces = {MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String test() {
        Map<String, String> pinfo = new HashMap<>();
        Context context = new Context();
        context.setVariable("pinfo", pinfo);
        pinfo.put("name", "Abai");
        pinfo.put("lastname", "Amangeldiuly");
        pinfo.put("email", "amangeldiuly@gmail.com");
        pinfo.put("gender", "male");
        pinfo.put("phone", "1234567890");
        pinfo.put("city", "Almaty");
        String content = templateEngine.process("personDetails", context);
        return content;
    }
}
