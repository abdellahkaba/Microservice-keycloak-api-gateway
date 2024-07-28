package com.isi.employe.employe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeRepository extends JpaRepository<Employe,String> {

    Optional<Employe> findByEmail(String email);
    Optional<Employe> findByTel(String tel);
}
