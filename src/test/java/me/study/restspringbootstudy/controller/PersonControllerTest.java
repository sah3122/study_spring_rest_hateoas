package me.study.restspringbootstudy.controller;

import me.study.restspringbootstudy.domain.Person;
import me.study.restspringbootstudy.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebMvcTest
public class PersonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonService personService;

    @Test
    public void person_가져오기() throws Exception {
        //given
        Person person = Person.builder()
                .name("dong")
                .age(10)
                .build();
        //when
        personService.save(person);

        //then
        mockMvc.perform(get("/api/persons/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}