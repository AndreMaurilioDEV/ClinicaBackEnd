package com.betrybe.Clinica.repository;

import com.betrybe.Clinica.entity.Person;
import com.betrybe.Clinica.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByUsername(String username);
    Optional<List<Person>> findByRole(Role role);
}
