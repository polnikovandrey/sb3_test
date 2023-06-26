package com.mcfly.springtemp.person_api.repository;

import com.mcfly.springtemp.person_api.entyty.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
