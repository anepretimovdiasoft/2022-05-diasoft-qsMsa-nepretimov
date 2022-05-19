package ru.diasoft.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.diasoft.domain.Person;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private long id;

    private String name;

    public static PersonDto toDto(Person person) {

        return PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .build();
    }

    public static Person fromDto(PersonDto personDto) {

        return Person.builder()
                .id(personDto.getId())
                .name(personDto.getName())
                .build();
    }

}
