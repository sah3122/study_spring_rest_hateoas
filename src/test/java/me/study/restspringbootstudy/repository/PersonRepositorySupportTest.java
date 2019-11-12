package me.study.restspringbootstudy.repository;

import me.study.restspringbootstudy.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonRepositorySupportTest {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonRepositorySupport personRepositorySupport;


    @Test
    public void querydsl_기본_기능_확인() {
        //given
        String name = "dongchul";
        int age = 27;
        personRepository.save(new Person(name, age));

        //when
        List<Person> result = personRepository.findByName(name);

        //then
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getName(), is(name));
    }

}