package com.isi.employe.employe;


import com.isi.employe.departement.DepartementResponse;
import org.springframework.stereotype.Service;

@Service
public class EmployeMapper {


    public Employe toEmploye(EmployeRequest request) {
        if (request == null){
            return null ;
        }
        return Employe.builder()
                .id(request.id())
                .prenom(request.prenom())
                .nom(request.nom())
                .email(request.email())
                .adresse(request.adresse())
                .dateNaissance(request.dateNaissance())
                .tel(request.tel())
                .statut(request.statut())
                .departementId(request.departementId())
                .build();
    }



    public EmployeResponse fromEmployeWithDepartement(Employe employe, DepartementResponse departement) {
        return new EmployeResponse(employe, departement);
    }
    public EmployeResponse1 fromEmploye(Employe employe){
        return new EmployeResponse1(
                employe.getId(),
                employe.getPrenom(),
                employe.getNom(),
                employe.getEmail(),
                employe.getAdresse(),
                employe.getDateNaissance(),
                employe.getTel(),
                employe.getStatut(),
                employe.getDepartementId()
        );
    }
}
