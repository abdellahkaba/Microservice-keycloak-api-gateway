package com.isi.employemicroservice.repository;

import com.isi.employemicroservice.entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Integer> {
    Optional<Employe> findByEmail(String email);
    Optional<Employe> findByNom(String nom);
    boolean existsByEmail(String email);
}
