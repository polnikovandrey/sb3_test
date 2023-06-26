package com.mcfly.springtemp;

import com.mcfly.springtemp.common.PersonNotFoundException;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void testGetPersonById() {
        final Person personB = new Person(2L, "B");
        Mockito.when(personRepository.findById(personB.getId())).thenReturn(Optional.of(personB));
        final Person foundPerson = personService.getPersonById(personB.getId());
        Mockito.verify(personRepository).findById(personB.getId());
        assertThat(foundPerson).isNotNull();
        assertThat(foundPerson.getId()).isEqualTo(personB.getId());
        assertThat(foundPerson.getName()).isEqualTo(personB.getName());
    }

    @Test
    public void testGetPersonByNotValidIdThrows() {
        Mockito.when(personRepository.findById(3L)).thenThrow(new PersonNotFoundException(3L));
        final Exception exception = assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(3L));
        assertThat(exception.getMessage()).contains(PersonNotFoundException.PERSON_NOT_FOUND_PREFIX + 3L);
    }
}
