package ru.diasoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.dao.PersonDao;
import ru.diasoft.domain.Person;
import ru.diasoft.exception.NotFindPersonException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;

    @Override
    @Transactional(readOnly = true)
    public List<Person> getAll() {
        return personDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Person getPersonById(long id) {
        return personDao
                .findById(id)
                .orElseThrow(NotFindPersonException::new);
    }

    @Override
    @Transactional
    public Person addPerson(Person person) {
        return personDao.save(person);
    }

    @Override
    @Transactional
    public Person updatePerson(long id, String name) {

        return personDao.save(
                Person.builder()
                        .id(id)
                        .name(name)
                        .build()
        );
    }

    @Override
    @Transactional
    public void deletePersonById(long id) {

        personDao.deleteById(id);
    }
}
