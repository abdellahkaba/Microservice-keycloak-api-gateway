package com.isi.departement.departement;

import jakarta.validation.constraints.NotNull;

public record DepartementRequest(
        Integer id,
        @NotNull(message = "Veuillez donner le nom d'un departement")
        String nom
) {
}
