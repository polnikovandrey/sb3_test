package com.mcfly.springtemp.controller;

import com.mcfly.springtemp.entyty.Person;
import com.mcfly.springtemp.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "/persons")
    public ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.ok().body(personService.getPersons());
    }

    @PostMapping(path = "/persons")
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        final Person newPerson = personService.createPerson(person);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @GetMapping(path = "/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        return ResponseEntity.ok().body(personService.getPersonById(id));
    }

    @DeleteMapping(path = "/persons/{id}")
    public ResponseEntity<String> deletePersonById(@PathVariable Long id) {
        final boolean deletePersonById = personService.deletePersonById(id);
        if (deletePersonById) {
            return new ResponseEntity<>("Person deleted, id: " + id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Person delete failed, id: " + id, HttpStatus.BAD_REQUEST);
        }
    }
}
