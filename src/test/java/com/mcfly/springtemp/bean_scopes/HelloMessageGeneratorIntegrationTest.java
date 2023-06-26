package com.mcfly.springtemp.bean_scopes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HelloMessageGeneratorIntegrationTest {

    @Autowired
    ApplicationContext context;

    @Test
    void contextLoads() {
        assertThat(context).isNotNull();
    }

    @Test
    void testPersonSingletonScope() {
        final HelloMessageGenerator helloMessageGeneratorA = (HelloMessageGenerator) context.getBean("singletonScopeHelloMessageGenerator");
        final HelloMessageGenerator helloMessageGeneratorB = (HelloMessageGenerator) context.getBean("singletonScopeHelloMessageGenerator");
        helloMessageGeneratorA.setMessage("A");
        assertThat(helloMessageGeneratorB.getMessage()).isEqualTo("A");
    }

    @Test
    void testPersonPrototypeScope() {
        final HelloMessageGenerator helloMessageGeneratorA = (HelloMessageGenerator) context.getBean("prototypeScopeHelloMessageGenerator");
        final HelloMessageGenerator helloMessageGeneratorB = (HelloMessageGenerator) context.getBean("prototypeScopeHelloMessageGenerator");
        helloMessageGeneratorA.setMessage("A");
        assertThat(helloMessageGeneratorB.getMessage()).isNull();
    }
}
