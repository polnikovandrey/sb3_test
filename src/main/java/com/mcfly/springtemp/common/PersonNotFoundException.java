package com.mcfly.springtemp.common;

public class PersonNotFoundException extends RuntimeException {

    public static final String PERSON_NOT_FOUND_PREFIX = "Person not found, id: ";

    public PersonNotFoundException(Long id) {
        super(PERSON_NOT_FOUND_PREFIX + id);
    }
}
