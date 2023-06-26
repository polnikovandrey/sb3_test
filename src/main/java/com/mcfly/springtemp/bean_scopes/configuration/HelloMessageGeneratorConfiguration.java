package com.mcfly.springtemp.bean_scopes.configuration;

import com.mcfly.springtemp.bean_scopes.common.HelloMessageGenerator;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

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
    @RequestScope
    public HelloMessageGenerator requestScopeHelloMessageGenerator() {
        return new HelloMessageGenerator();
    }

    @Bean
    @SessionScope
    public HelloMessageGenerator sessionScopeHelloMessageGenerator() {
        return new HelloMessageGenerator();
    }

    @Bean
    @ApplicationScope
    public HelloMessageGenerator applicationScopeHelloMessageGenerator() {
        return new HelloMessageGenerator();
    }

    /*
      When first accessed, WebSocket scoped beans are stored in the WebSocket session attributes.
      The same instance of the bean is then returned whenever that bean is accessed during the entire WebSocket session.
      We can also say that it exhibits singleton behavior, but limited to a WebSocket session only.
      */
    @Bean
    @Scope(value = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public HelloMessageGenerator websocketScopeHelloMessageGenerator() {
        return new HelloMessageGenerator();
    }

}
