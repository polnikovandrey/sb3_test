package com.mcfly.springtemp.bean_scopes.controller;

import com.mcfly.springtemp.bean_scopes.common.HelloMessageGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloMessageController {

    @Resource(name = "requestScopeHelloMessageGenerator")
    HelloMessageGenerator requestScopeHelloMessageGenerator;

    @Resource(name = "sessionScopeHelloMessageGenerator")
    HelloMessageGenerator sessionScopeHelloMessageGenerator;

    @Resource(name = "applicationScopeHelloMessageGenerator")
    HelloMessageGenerator applicationScopeHelloMessageGenerator;

    @RequestMapping("/scopes/request")
    public String getRequestScopeMessage(Model model) {
        model.addAttribute("previousMessage", requestScopeHelloMessageGenerator.getMessage());
        requestScopeHelloMessageGenerator.setMessage("Good morning!");
        model.addAttribute("currentMessage", requestScopeHelloMessageGenerator.getMessage());
        return "bean_scopes/scopesExample";
    }

    @RequestMapping("/scopes/session")
    public String getSessionScopeMessage(Model model) {
        model.addAttribute("previousMessage", sessionScopeHelloMessageGenerator.getMessage());
        sessionScopeHelloMessageGenerator.setMessage("Good afternoon!");
        model.addAttribute("currentMessage", sessionScopeHelloMessageGenerator.getMessage());
        return "bean_scopes/scopesExample";
    }

    @RequestMapping("/scopes/application")
    public String getApplicationScopeMessage(Model model) {
        model.addAttribute("previousMessage", applicationScopeHelloMessageGenerator.getMessage());
        applicationScopeHelloMessageGenerator.setMessage("Good evening!");
        model.addAttribute("currentMessage", applicationScopeHelloMessageGenerator.getMessage());
        return "bean_scopes/scopesExample";
    }
}
