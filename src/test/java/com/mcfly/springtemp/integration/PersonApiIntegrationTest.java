package com.mcfly.springtemp.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcfly.springtemp.entyty.Person;
import com.mcfly.springtemp.repository.PersonRepository;
import com.mcfly.springtemp.service.PersonService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// @SpringBootTest: complete Spring application context + mock web environment
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonApiIntegrationTest {

    private static HttpHeaders httpHeaders;

    @LocalServerPort        // used to get a random port, allocated by @SpringBootTest
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;      // testing version of RestTemplate class, used to perform api calls

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void init() {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Sql(statements = "insert into person(id, name) values (2, 'B')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "delete from person where id = 2", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testPersonsList() {
        final HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
        final ResponseEntity<List<Person>> response = testRestTemplate.exchange(createUrlWithPort(), HttpMethod.GET, entity, new ParameterizedTypeReference<>() { });
        final List<Person> personList = response.getBody();
        assertThat(personList).isNotNull();
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(personList.size()).isEqualTo(personService.getPersons().size());
        assertThat(personList.size()).isEqualTo(personRepository.findAll().size());
    }

    @Test
    @Sql(statements = "insert into person(id, name) values (2, 'B')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "delete from person where id = 2", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testPersonById() throws JsonProcessingException {
        final HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
        final ResponseEntity<Person> response = testRestTemplate.exchange(createUrlWithPort() + "/2", HttpMethod.GET, entity, Person.class);
        final Person responseBodyPerson = response.getBody();
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(objectMapper.writeValueAsString(responseBodyPerson)).isEqualTo("{\"id\":2,\"name\":\"B\"}");
        assertThat(responseBodyPerson).isNotNull();
        assertThat(responseBodyPerson).isEqualTo(personService.getPersonById(2L));
        assertThat(responseBodyPerson).isEqualTo(personRepository.findById(2L).get());
        

    }


    private String createUrlWithPort() {
        return "http://localhost:" + port + "/api/persons";
    }

}
