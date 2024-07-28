package com.isi.departement.departement;

import org.springframework.stereotype.Component;

@Component
public class DepartementMapper {


    public Departement toDepartement(DepartementRequest request) {
        if (request == null){
            return null ;
        }
        return Departement.builder()
                .id(request.id())
                .nom(request.nom())
                .build();
    }

    public DepartementResponse fromDepartement(Departement departement) {
        return new DepartementResponse(
                departement.getId(),
                departement.getNom()
        );
    }

}
