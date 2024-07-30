package com.isi.employe.employe;

import java.time.LocalDate;

public record EmployeResponse1(
        String id,
        String prenom,
        String nom,
        String email,
        String adresse,
        LocalDate dateNaissance,
        String tel,
        Statut statut,
        Integer departementId,
        Integer postId
) {
}
