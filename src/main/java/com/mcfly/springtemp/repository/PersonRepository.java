package com.mcfly.springtemp.repository;

import com.mcfly.springtemp.entyty.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository {

    List<Person> findAll();

}
