package me.study.restspringbootstudy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.study.restspringbootstudy.domain.Person;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class PersonDto  extends RepresentationModel<PersonDto> {

    private Long id;

    private String name;

    private int age;

    public PersonDto(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.age = person.getAge();
    }

    public Person toPersonEntity(){
        return Person.builder()
                .age(this.age)
                .name(this.name)
                .build();
    }
}
