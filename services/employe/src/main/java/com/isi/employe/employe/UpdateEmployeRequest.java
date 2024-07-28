package com.isi.employe.employe;

import jakarta.validation.constraints.Email;

import java.time.LocalDate;

public record UpdateEmployeRequest(
        String id,
        String prenom,
        String nom,
        @Email(message = "Le mail n'est pas valide")
        String email,
        String adresse,
        LocalDate dateNaissance,
        String tel,
        Statut statut,
        Integer departementId
) {
}
