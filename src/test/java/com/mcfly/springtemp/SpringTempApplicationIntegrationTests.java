package com.mcfly.springtemp;

import com.mcfly.springtemp.entyty.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringTempApplicationIntegrationTests {

    @Autowired
    ApplicationContext context;

    @Test
    void contextLoads() {
        assertThat(context).isNotNull();
    }

    @Test
    void testPersonSingletonScope() {
        final Person personA = (Person) context.getBean("personSingletonScopeBean");
        final Person personB = (Person) context.getBean("personSingletonScopeBean");
        personA.setName("John");
        assertThat(personB.getName()).isEqualTo("John");
    }

    @Test
    void testPersonPrototypeScope() {
        final Person personA = (Person) context.getBean("personPrototypeScopeBean");
        final Person personB = (Person) context.getBean("personPrototypeScopeBean");
        personA.setName("A");
        personB.setName("B");
        assertThat(personA.getName()).isEqualTo("A");
        assertThat(personB.getName()).isEqualTo("B");
    }
}
