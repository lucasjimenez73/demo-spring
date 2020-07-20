package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    default int insertPerson(Person person){
        UUID id=UUID.randomUUID();
        return instertPerson(id,person);
    }

    int instertPerson(UUID id, Person person);

    List<Person> selectAllPeople();

    Optional<Person> getPersonById(UUID id);

    int deletePersonById(UUID id);

    int updatePeronById(UUID id,Person person);


}
