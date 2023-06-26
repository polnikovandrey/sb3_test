package com.mcfly.springtemp.repository;

import com.mcfly.springtemp.entyty.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
