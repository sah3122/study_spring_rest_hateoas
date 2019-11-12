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

        return ResponseEntity.ok(person);
    }

    @GetMapping("/persons")
    public ResponseEntity getPersonList() {
        return ResponseEntity.of(null);
    }

    @PostMapping("/persons")
    public ResponseEntity createPerson(@RequestBody Person person) {
        personService.save(person);
        person.add(new Link("http://" +  host + "/api/persons/" + person.getId()));

        return ResponseEntity.ok(person);
    }

    @PutMapping("/persons")
    public ResponseEntity updatePerson(@RequestBody Person person) {
        return ResponseEntity.of(null);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity deletePerson(@PathVariable(name = "id") Long id) {
        return ResponseEntity.of(null);
    }
}
