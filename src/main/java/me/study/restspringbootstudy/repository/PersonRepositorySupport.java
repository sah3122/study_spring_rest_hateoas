package me.study.restspringbootstudy.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.study.restspringbootstudy.domain.Person;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.study.restspringbootstudy.domain.QPerson.person;

@Repository
public class PersonRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public PersonRepositorySupport(JPAQueryFactory queryFactory) {
        super(Person.class);
        this.queryFactory = queryFactory;
    }

    public List<Person> findByName(String name) {
        return queryFactory
                .selectFrom(person)
                .where(person.name.eq(name))
                .fetch();

    }
}
