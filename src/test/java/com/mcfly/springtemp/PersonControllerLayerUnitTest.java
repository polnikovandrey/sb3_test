package com.mcfly.springtemp;

import com.mcfly.springtemp.entyty.Person;
import com.mcfly.springtemp.service.PersonService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)      // integrates the Spring TestContext Framework into JUnit 5’s Jupiter programming model.
@WebMvcTest     // apply only configuration relevant to MVC tests, not @Component, @Service or @Repository beans
public class PersonControllerLayerUnitTest {

    @Autowired
    MockMvc mockMvc;        // helps in testing the controllers explicitly starting a Servlet container

    @MockBean
    PersonService personService;        // add mock objects to the Spring application context

    @Test
    public void testGetPersonsList() throws Exception {
        final Person personA = new Person(1L, "A");
        when(personService.getPersons()).thenReturn(Collections.singletonList(personA));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }
}
