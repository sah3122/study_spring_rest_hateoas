package me.study.restspringbootstudy.repository;

import me.study.restspringbootstudy.domain.Person;

import java.util.List;

public interface PersonRepositoryCustom {
    List<Person> findByName(String name);
}
