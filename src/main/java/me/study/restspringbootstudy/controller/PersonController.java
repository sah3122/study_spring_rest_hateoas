package me.study.restspringbootstudy.controller;

import lombok.RequiredArgsConstructor;
import me.study.restspringbootstudy.domain.Person;
import me.study.restspringbootstudy.service.PersonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PersonController {
    @Value("${serverhost}")
    private String host;

    private final PersonService personService;


    @GetMapping("/persons/{id}")
    public ResponseEntity getPerson(@PathVariable(name = "id") Long id) {

        Person person = personService.findOne(id);
        person.add(new Link("http://" +  host + "/api/persons/" + person.getId()).withRel("self2"));
        Link link = WebMvcLinkBuilder.linkTo(PersonController.class).withRel("person");
        Link selflink = WebMvcLinkBuilder.linkTo(PersonController.class).slash("persons").slash(person.getId()).withSelfRel();
        person.add(link);
        person.add(selflink);

        return ResponseEntity.ok(person);
    }

    @GetMapping("/persons")
    public ResponseEntity getPersonList() {
        List<Person> personList = personService.findAll();
        personList.forEach(person -> {
            Link selflink = WebMvcLinkBuilder.linkTo(PersonController.class).slash("persons").slash(person.getId()).withSelfRel();
            person.add(selflink);
        });
        return ResponseEntity.ok(personList);
    }

    @PostMapping("/persons")
    public ResponseEntity createPerson(@RequestBody Person person) {
        personService.save(person);
        person.add(new Link("http://" +  host + "/api/persons/" + person.getId()));

        return ResponseEntity.ok(person);
    }

    @PutMapping("/persons")
    public ResponseEntity updatePerson(@RequestBody Person person) {
        Person persons = personService.updatePerson(person.getId(), person.getName(), person.getAge());

        Link selflink = WebMvcLinkBuilder.linkTo(PersonController.class).slash("persons").slash(person.getId()).withSelfRel();
        persons.add(selflink);
        return ResponseEntity.ok(persons);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity deletePerson(@PathVariable(name = "id") Long id) {
        return ResponseEntity.of(null);
    }
}
