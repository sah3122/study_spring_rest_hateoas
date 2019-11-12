package me.study.restspringbootstudy.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.study.restspringbootstudy.domain.Person;

import java.util.List;

import static me.study.restspringbootstudy.domain.QPerson.person;

@RequiredArgsConstructor
public class PersonRepositoryImpl implements PersonRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Person> findByName(String name) {
        return jpaQueryFactory.selectFrom(person)
                .where(person.name.eq(name))
                .fetch();
    }
}
