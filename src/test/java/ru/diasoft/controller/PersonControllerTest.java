package ru.diasoft.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.diasoft.controller.dto.PersonDto;
import ru.diasoft.domain.Person;
import ru.diasoft.service.PersonService;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
@DisplayName("Класс PersonServiceImpl")
class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private PersonService personService;

    @Test
    @DisplayName("должен возвращать всех людей")
    void shouldGetAll() throws Exception {
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

        List<Person> people = List.of(expectedPerson1, expectedPerson2, expectedPerson3);
        given(personService.getAll()).willReturn(people);

        List<PersonDto> expectedResult = people.stream()
                .map(PersonDto::toDto)
                .collect(Collectors.toList());

        mvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    @DisplayName("должен возвращать человека по id")
    void shouldGetPersonById() throws Exception {
        Person expectedPerson = Person.builder()
                .id(1)
                .name("Ivan")
                .build();
        given(personService.getPersonById(1L)).willReturn(expectedPerson);

        PersonDto expectedResult = PersonDto.toDto(expectedPerson);

        mvc.perform(get("/person/" + 1L))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    @DisplayName("должен добавлять человека")
    void shouldAddPerson() throws Exception {
        Person expectedPerson = Person.builder()
                .id(1)
                .name("Ivan")
                .build();

        given(personService.addPerson(expectedPerson)).willReturn(expectedPerson);

        PersonDto expectedResult = PersonDto.toDto(expectedPerson);

        mvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(expectedResult)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    @DisplayName("должен обновлять человека")
    void shouldUpdatePerson() throws Exception {
        Person expectedPerson = Person.builder()
                .id(1)
                .name("Ivan")
                .build();
        given(personService.updatePerson(1L, "Ivan")).willReturn(expectedPerson);

        PersonDto expectedResult = PersonDto.toDto(expectedPerson);

        mvc.perform(put("/person/1")
                        .param("name", "Ivan"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    @DisplayName("должен удалять человека по id")
    void shouldDeletePersonById() throws Exception {
        mvc.perform(delete("/person/" + 1))
                .andExpect(status().isOk());

        verify(personService, times(1)).deletePersonById(1);
    }
}