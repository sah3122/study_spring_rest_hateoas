package me.study.restspringbootstudy.repository;

import lombok.RequiredArgsConstructor;
import me.study.restspringbootstudy.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryCustom {
}
