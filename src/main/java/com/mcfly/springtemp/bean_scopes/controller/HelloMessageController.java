package com.mcfly.springtemp.bean_scopes.controller;

import com.mcfly.springtemp.bean_scopes.HelloMessageGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;

@Controller
public class HelloMessageController {

    @Resource(name = "requestScopeHelloMessageGenerator")
    HelloMessageGenerator helloMessageGenerator;

}
