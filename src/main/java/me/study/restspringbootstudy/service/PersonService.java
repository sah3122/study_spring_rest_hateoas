package me.study.restspringbootstudy.service;

import lombok.RequiredArgsConstructor;
import me.study.restspringbootstudy.domain.Person;
import me.study.restspringbootstudy.dto.PersonDto;
import me.study.restspringbootstudy.repository.PersonRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public PersonDto findOne(Long id) {
        Optional<Person> person = personRepository.findById(id);
        Person findPerson = person.orElseThrow(() -> new RuntimeException());
        PersonDto personDto = PersonDto.builder()
                .age(findPerson.getAge())
                .name(findPerson.getName())
                .build();


        return personDto;
    }

    public List<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

    public List<Person> findAll() {
        List<Person> personList = personRepository.findAll();
        return personList;
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public Person updatePerson(Long id, String name, int age) {
        Optional<Person> findPerson = personRepository.findById(id);

        Person person = findPerson.orElseGet(() -> new Person());
        person.setName(name);
        person.setAge(age);

        return person;
    }
}
