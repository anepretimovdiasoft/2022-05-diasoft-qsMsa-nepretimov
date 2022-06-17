package ru.diasoft.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.diasoft.domain.Person;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Класс PersonDaoTest")
class PersonDaoTest {

    @Autowired
    PersonDao dao;

    @DisplayName("должен добавлять человека")
    @Test
    void shouldInsertPerson() {

        Person expectedPerson = Person.builder()
                .id(4)
                .name("Ivan")
                .build();


        dao.save(expectedPerson);
        Person actualPerson = dao.getById(4L);

        assertThat(actualPerson).isEqualTo(expectedPerson);
    }

    @DisplayName("должен обновлять человека")
    @Test
    void shouldUpdatePerson() {
        Person expectedPerson = Person.builder()
                .id(1)
                .name("Ivan")
                .build();

        dao.save(expectedPerson);
        Person actualPerson = dao.getById(1L);

        assertThat(actualPerson).isEqualTo(expectedPerson);
    }

    @DisplayName("должен возвращать всех людей")
    @Test
    void shouldGetAllPersons() {

        Person expectedPerson1 = Person.builder()
                .id(1)
                .name("Name 1")
                .build();
        Person expectedPerson2 = Person.builder()
                .id(2)
                .name("Name 2")
                .build();
        Person expectedPerson3 = Person.builder()
                .id(3)
                .name("Name 3")
                .build();

        assertThat(dao.findAll().size()).isEqualTo(3);
        assertThat(dao.findAll())
                .containsExactlyInAnyOrder(expectedPerson1, expectedPerson2, expectedPerson3);
    }

    @DisplayName("должен возвращать человека по id")
    @Test
    void shouldGetPersonById() {

        Person expectedPerson = Person.builder()
                .id(1)
                .name("Name 1")
                .build();

        Person actualPerson = dao.getById(1L);

        assertThat(actualPerson).isEqualTo(expectedPerson);
    }


    @DisplayName("должен удалять человека по id")
    @Test
    void shouldDeletePersonById() {

        int sizeBefore = dao.findAll().size();

        dao.deleteById(1L);

        int sizeAfter = dao.findAll().size();
        assertThat(sizeBefore - sizeAfter).isEqualTo(1);
    }

}