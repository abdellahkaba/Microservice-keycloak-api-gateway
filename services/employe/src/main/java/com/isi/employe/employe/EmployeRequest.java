package com.isi.employe.employe;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmployeRequest(
        String id,
        @NotNull(message = "Veuillez saisir le prenom")
        String prenom,
        @NotNull(message = "Veuillez saisir le nom")
        String nom,
        @NotNull(message = "Veuillez saisir l'email")
        //   @Column(unique = true)
        @Email(message = "le mail n'est pas valide")
        String email,
        @NotNull(message = "Veuillez saisir l'adresse")
        String adresse,
        @NotNull(message = "Veuillez saisir la date")
        LocalDate dateNaissance,
        @NotNull(message = "Veuillez saisir la date")
        String tel,
        @NotNull(message = "Veuillez donner le status de l'employè")
        Statut statut,
        @NotNull(message = "Un employe doit etre dans departement")
        Integer departementId
) {
}
