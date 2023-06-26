package com.mcfly.springtemp;

import com.mcfly.springtemp.entyty.Person;
import com.mcfly.springtemp.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest        // test environment for JPA layer
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)      // tests use H2 in-memory database
public class PersonRepositoryLayerUnitTest {

    @Autowired
    PersonRepository personRepository;      // Unit test doesn't need to mock repo, as soon as special db is used for testing.

    @BeforeEach
    public void setUp() {
        personRepository.save(new Person(null, "A"));
        personRepository.save(new Person(null, "B"));
    }

    @AfterEach
    public void destroy() {
        personRepository.deleteAll();
    }

    @Test
    public void testGetAllPersons() {
        final List<Person> persons = personRepository.findAll();
        assertThat(persons).isNotNull();
        assertThat(persons.size()).isEqualTo(2);
        assertThat(persons.get(0).getId()).isNotNegative().isGreaterThan(0);
        assertThat(persons.get(0).getName()).isEqualTo("A");
        assertThat(persons.get(1).getId()).isNotNegative().isGreaterThan(0);
        assertThat(persons.get(1).getName()).isEqualTo("B");
    }
}
