package ru.diasoft.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.dao.PersonDao;
import ru.diasoft.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@DisplayName("Класс PersonServiceImpl")
class PersonServiceImplTest {

    PersonService personService;

    @Mock
    PersonDao personDao;

    List<Person> people;

    @BeforeEach
    void init() {
        people = new ArrayList<>();

        Person expectedPerson1 = Person.builder()
                .id(1)
                .name("name1")
                .build();
        Person expectedPerson2 = Person.builder()
                .id(2)
                .name("name2")
                .build();
        Person expectedPerson3 = Person.builder()
                .id(3)
                .name("name3")
                .build();

        people.add(expectedPerson1);
        people.add(expectedPerson2);
        people.add(expectedPerson3);

        personService = new PersonServiceImpl(personDao);
    }

    @Test
    @DisplayName("должен возвращать всех людей")
    void shouldGetAll() {
        when(personDao.findAll()).thenReturn(people);

        List<Person> expectedPerson = personService.getAll();

        assertThat(expectedPerson).isEqualTo(people);
    }

    @Test
    @DisplayName("должен возвращать человека по id")
    void shouldGetPersonById() {
        Person expectedPerson = Person.builder()
                .id(4)
                .name("Ivan")
                .build();

        when(personDao.findById(4L)).thenReturn(Optional.of(expectedPerson));

        Person actualPerson = personService.getPersonById(4L);

        assertThat(expectedPerson).isEqualTo(actualPerson);
    }

    @Test
    @DisplayName("должен добавлять человека")
    void shouldAddPerson() {
        Person expectedPerson = Person.builder()
                .id(4)
                .name("Ivan")
                .build();

        when(personDao.save(expectedPerson)).thenReturn(expectedPerson);

        Person actualPerson = personService.addPerson(expectedPerson);

        assertThat(expectedPerson).isEqualTo(actualPerson);
    }

    @Test
    @DisplayName("должен обновлять человека")
    void shouldUpdatePerson() {
        Person expectedPerson = Person.builder()
                .id(1)
                .name("Ivan")
                .build();

        when(personDao.save(expectedPerson)).thenReturn(expectedPerson);

        Person actualPerson = personService.updatePerson(1, "Ivan");

        assertThat(expectedPerson).isEqualTo(actualPerson);
    }

}