package com.mcfly.springtemp.person_api.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcfly.springtemp.person_api.entyty.Person;
import com.mcfly.springtemp.person_api.repository.PersonRepository;
import com.mcfly.springtemp.person_api.service.PersonService;
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
    public void testGetPersons() {
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
    public void testGetPersonById() throws JsonProcessingException {
        final HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
        final ResponseEntity<Person> response = testRestTemplate.exchange(createUrlWithPort() + "/2", HttpMethod.GET, entity, Person.class);
        final Person responseBodyPerson = response.getBody();
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(responseBodyPerson)
                .isNotNull()
                .isEqualTo(personService.getPersonById(2L))
                .isEqualTo(personRepository.findById(2L).get());
        assertThat(objectMapper.writeValueAsString(responseBodyPerson)).isEqualTo("{\"id\":2,\"name\":\"B\"}");
    }

    @Test
    @Sql(statements = "delete from person where name = 'Test dummy'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testCreatePerson() throws JsonProcessingException {
        final Person dummyPerson = new Person(null, "Test dummy");
        final HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(dummyPerson), httpHeaders);
        final ResponseEntity<Person> response = testRestTemplate.exchange(createUrlWithPort(), HttpMethod.POST, entity, Person.class);
        final Person responseBodyPerson = response.getBody();
        assertThat(response.getStatusCode().value()).isEqualTo(201);
        assertThat(responseBodyPerson)
                .isNotNull()
                .isEqualTo(personService.getPersonById(responseBodyPerson.getId()))
                .isEqualTo(personRepository.findById(responseBodyPerson.getId()).get());
        assertThat(objectMapper.writeValueAsString(responseBodyPerson)).isEqualTo("{\"id\":" + responseBodyPerson.getId() + ",\"name\":\"Test dummy\"}");
    }

    @Test
    @Sql(statements = "insert into person (id, name) values (1, 'A')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "delete from person where id = 1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testDeletePersonById() {
        final ResponseEntity<String> response = testRestTemplate.exchange(createUrlWithPort() + "/1", HttpMethod.DELETE, null, String.class);
        final String responseBody = response.getBody();
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(responseBody).isNotNull().isEqualTo("Person deleted, id: 1");
    }

    private String createUrlWithPort() {
        return "http://localhost:" + port + "/api/persons";
    }

}
