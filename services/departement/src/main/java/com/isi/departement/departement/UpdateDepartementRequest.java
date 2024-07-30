package com.isi.departement.departement;

public record UpdateDepartementRequest(
        Integer id,
        String nom,
        String description
) {
}
