package com.mcfly.springtemp.bean_scopes.configuration;

import com.mcfly.springtemp.bean_scopes.common.HelloMessageGenerator;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class HelloMessageGeneratorConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public HelloMessageGenerator singletonScopeHelloMessageGenerator() {
        return new HelloMessageGenerator();
    }

    @Bean
    @Scope("prototype")
    public HelloMessageGenerator prototypeScopeHelloMessageGenerator() {
        return new HelloMessageGenerator();
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public HelloMessageGenerator requestScopeHelloMessageGenerator() {
        return new HelloMessageGenerator();
    }

}
