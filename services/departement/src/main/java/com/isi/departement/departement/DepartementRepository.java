package com.isi.departement.departement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartementRepository extends JpaRepository<Departement,Integer> {
    Optional<Departement> findByNom(String nom);
}
