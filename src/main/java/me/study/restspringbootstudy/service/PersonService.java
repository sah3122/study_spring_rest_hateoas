package me.study.restspringbootstudy.service;

import lombok.RequiredArgsConstructor;
import me.study.restspringbootstudy.domain.Person;
import me.study.restspringbootstudy.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person findOne(Long id) {
        Optional<Person> person = personRepository.findById(id);

        return person.orElseThrow(() -> new RuntimeException());
    }

    public void save(Person person) {
        personRepository.save(person);
    }
}
