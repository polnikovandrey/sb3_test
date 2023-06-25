package com.mcfly.springtemp;

import com.mcfly.springtemp.entyty.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringTempApplicationTests {

    @Autowired
    ApplicationContext context;

    @Test
    void contextLoads() {
    }

    @Test
    void testSingletonScope() {
        final Person personA = (Person) context.getBean("personSingleton");
        final Person personB = (Person) context.getBean("personSingleton");
        personA.setName("John");
        assertThat(personB.getName()).isEqualTo("John");
    }
}
