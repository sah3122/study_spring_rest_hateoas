package me.study.restspringbootstudy.controller;

import lombok.RequiredArgsConstructor;
import me.study.restspringbootstudy.domain.Person;
import me.study.restspringbootstudy.dto.PersonDto;
import me.study.restspringbootstudy.service.PersonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PersonController {
    @Value("${serverhost}")
    private String host;

    private final PersonService personService;


    @GetMapping("/persons/{id}")
    public ResponseEntity getPerson(@PathVariable(name = "id") Long id) {

        PersonDto person = personService.findOne(id);
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
        List<PersonDto> personDtoList = personList.stream().map(PersonDto::new).collect(Collectors.toList());
        personDtoList.forEach(person -> {
            Link selflink = WebMvcLinkBuilder.linkTo(PersonController.class).slash("persons").slash(person.getId()).withSelfRel();
            person.add(selflink);
        });
        return ResponseEntity.ok(personDtoList);
    }

    @PostMapping("/persons")
    public ResponseEntity createPerson(@RequestBody PersonDto personDto) {
        personService.save(personDto.toPersonEntity());
        personDto.add(new Link("http://" +  host + "/api/persons/" + personDto.getId()));

        return ResponseEntity.ok(personDto);
    }

    @PutMapping("/persons")
    public ResponseEntity updatePerson(@RequestBody PersonDto personDto) {
        Person persons = personService.updatePerson(personDto.getId(), personDto.getName(), personDto.getAge());
        personDto = new PersonDto(persons);
        Link selflink = WebMvcLinkBuilder.linkTo(PersonController.class).slash("persons").slash(personDto.getId()).withSelfRel();
        personDto.add(selflink);
        return ResponseEntity.ok(personDto);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity deletePerson(@PathVariable(name = "id") Long id) {
        return ResponseEntity.of(null);
    }
}
