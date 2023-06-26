package com.mcfly.springtemp.configuration;

import com.mcfly.springtemp.common.HelloMessageGenerator;
import com.mcfly.springtemp.entyty.Person;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class ApplicationConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Person personSingletonScopeBean() {
        return new Person();
    }

    @Bean
    @Scope("prototype")
    public Person personPrototypeScopeBean() {
        return new Person();
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public HelloMessageGenerator helloMessageGeneratorRequestScopeBean() {
        return new HelloMessageGenerator();
    }
}
