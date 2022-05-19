package ru.diasoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.diasoft.controller.dto.PersonDto;
import ru.diasoft.domain.Person;
import ru.diasoft.service.PersonService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/person")
    public ResponseEntity<List<PersonDto>> getAll() {

        return ResponseEntity.ok()
                .body(
                        personService.getAll()
                                .stream()
                                .map(PersonDto::toDto)
                                .collect(Collectors.toList())
                );
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable long id) {

        return ResponseEntity.ok()
                .body(
                        PersonDto.toDto(personService.getPersonById(id))
                );
    }

    @PostMapping("/person")
    public ResponseEntity<PersonDto> addPerson(@RequestBody PersonDto personDto) {

        return ResponseEntity.ok(
                PersonDto.toDto(
                        personService.addPerson(PersonDto.fromDto(personDto))
                )
        );
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable long id, @RequestParam String name) {

        return ResponseEntity.ok(
                PersonDto.toDto(
                        personService.updatePerson(id, name)
                )
        );
    }

    @DeleteMapping("/person/{id}")
    public void deletePersonById(@PathVariable long id) {

        personService.deletePersonById(id);
    }

}
