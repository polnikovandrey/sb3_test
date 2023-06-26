package com.mcfly.springtemp.person_api.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Test
    public void testGetPersonById() throws Exception {
        final Person personB = new Person(2L, "B");
        when(personService.getPersonById(2L)).thenReturn(personB);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("B")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    public void testCreatePerson() throws Exception {
        final Person toCreate = new Person(null, "C");
        final Person personC = new Person(3L, "C");
        when(personService.createPerson(toCreate)).thenReturn(personC);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/persons").content(new ObjectMapper().writeValueAsString(toCreate)).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("C")))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    public void testDeletePerson() throws Exception {
        final Person personD = new Person(4L, "D");
        when(personService.deletePersonById(personD.getId())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/persons/4"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
