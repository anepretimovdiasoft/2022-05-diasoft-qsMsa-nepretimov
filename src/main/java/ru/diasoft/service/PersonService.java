package ru.diasoft.service;

import ru.diasoft.domain.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAll();

    Person getPersonById(long id);

    Person addPerson(Person person);

    Person updatePerson(long id, String name);

    void deletePersonById(long id);

}
