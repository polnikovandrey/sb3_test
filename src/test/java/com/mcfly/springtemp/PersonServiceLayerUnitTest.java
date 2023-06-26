package com.mcfly.springtemp;

import com.mcfly.springtemp.entyty.Person;
import com.mcfly.springtemp.repository.PersonRepository;
import com.mcfly.springtemp.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class PersonServiceLayerUnitTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    @Test
    public void testGetPersonsList() {
        final Person personA = new Person(1L, "A");
        final Person personB = new Person(1L, "B");
        Mockito.when(personRepository.findAll()).thenReturn(Arrays.asList(personA, personB));
        final List<Person> persons = personService.getPersons();
        Mockito.verify(personRepository).findAll();     // assert personService.getPersons() calls personRepository.findAll()
        assertThat(persons).isNotNull().hasSize(2);
        assertThat(persons.get(0).getName()).isEqualTo(personA.getName());
        assertThat(persons.get(1).getName()).isEqualTo(personB.getName());
    }
}
