package ru.diasoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diasoft.domain.Person;

public interface PersonDao extends JpaRepository<Person, Long> {
}
