package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{

    private static List<Person> DB=new ArrayList();

    @Override
    public int instertPerson(UUID id, Person person) {
        DB.add( new Person(id,person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> mayBePerson = getPersonById(id);
        if(mayBePerson.isEmpty()){
            return 0;
        }
        DB.remove(mayBePerson.get());
        return 1;
    }

    @Override
    public int updatePeronById(UUID id, Person person) {
        return getPersonById(id)
               .map(p-> {
                   int indexOfPersonToUpdate = DB.indexOf(p);
                   if(indexOfPersonToUpdate>=0){
                       DB.set(indexOfPersonToUpdate,new Person(id,person.getName()));
                       return 1;
                   }
                   return 0;
               })
                .orElse(0);
    }


}
