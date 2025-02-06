package com.betrybe.hospitalExample.repository;

import com.betrybe.hospitalExample.entity.Person;
import com.betrybe.hospitalExample.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByUsername(String username);
    Optional<List<Person>> findByRole(Role role);
}
