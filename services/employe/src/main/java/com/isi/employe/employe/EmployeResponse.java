package com.isi.employe.employe;

import com.isi.employe.departement.DepartementResponse;

import java.time.LocalDate;

public record EmployeResponse(
        String id,
        String prenom,
        String nom,
        String email,
        String adresse,
        LocalDate dateNaissance,
        Statut statut,
        Integer departementId,
        DepartementResponse departementResponse
) {
    public EmployeResponse(Employe employe, DepartementResponse departement) {
        this(
                employe.getId(),
                employe.getPrenom(),
                employe.getNom(),
                employe.getEmail(),
                employe.getAdresse(),
                employe.getDateNaissance(),
                employe.getStatut(),
                employe.getDepartementId(),
                departement
        );
    }
}
