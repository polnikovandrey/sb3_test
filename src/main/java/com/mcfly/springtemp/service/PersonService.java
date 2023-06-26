package com.mcfly.springtemp.service;

import com.mcfly.springtemp.common.PersonNotFoundException;
import com.mcfly.springtemp.entyty.Person;
import com.mcfly.springtemp.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> throwPersonNotFoundException(id));
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public boolean deletePersonById(Long id) {
        final Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            personRepository.deleteById(id);
            return true;
        } else {
            throwPersonNotFoundException(id);
            return false;
        }
    }

    private PersonNotFoundException throwPersonNotFoundException(Long id) {
        throw new PersonNotFoundException("Person not found, id: " + id);
    }
}
