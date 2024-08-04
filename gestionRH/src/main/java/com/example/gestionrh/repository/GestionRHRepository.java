package com.example.gestionrh.repository;

import com.example.gestionrh.entities.GestionnaireRH;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestionRHRepository extends JpaRepository<GestionnaireRH,Integer> {

    //Verification si l'addresse n'existe pas et phone aussi
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
