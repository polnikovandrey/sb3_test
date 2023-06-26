package com.mcfly.springtemp;

import com.mcfly.springtemp.entyty.Person;
import com.mcfly.springtemp.repository.PersonRepository;
import com.mcfly.springtemp.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SpringTempApplicationUnitTests {

    @Mock
    private PersonRepository personRepository;

    private PersonService personService;

    @BeforeEach
    void setUp() {
        this.personService = new PersonService(personRepository);
    }

    @Test
    void getPersonServiceGetAllPersonCallsPersonRepoitoryFindAll() {
        personService.getPersons();
        verify(personRepository).findAll();
    }

    @Test
    void getPersonList() {
        final Person personA = new Person(1L, "A");
        final Person personB = new Person(2L, "B");
        when(personRepository.findAll()).thenReturn(Arrays.asList(personA, personB));
        final List<Person> persons = personService.getPersons();
        assertThat(persons).isNotNull().hasSize(2);
        assertThat(persons.get(0).getName()).isEqualTo(personA.getName());
        assertThat(persons.get(1).getName()).isEqualTo(personB.getName());
    }
}
