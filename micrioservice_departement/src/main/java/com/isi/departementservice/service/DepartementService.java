package com.isi.departementservice.service;

import com.isi.departementservice.entity.Departement;
import com.isi.departementservice.repository.DepartementRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;





    public Departement addDepartement(Departement departement) {
        if (departementRepository.findByNomDepartement(departement.getNomDepartement()).isPresent()) {
            throw new RuntimeException("Le nom du département existe déjà");
        }
        if (departement.getNomDepartement().length() > 100) {
            throw new RuntimeException("Le nom du département ne doit pas dépasser 100 caractères");
        }
        return departementRepository.save(departement);
    }

    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }


    public Optional<Departement> getDepartementById(int id) {
        return departementRepository.findById(id);
    }

    public Optional<Departement> getDepartementByName(String nom) {
        return departementRepository.findByNomDepartement(nom);
    }

    public Departement updateDepartement(int id, Departement detailsDepartement) {
        Departement departement = departementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Département non trouvé"));

        if (detailsDepartement.getNomDepartement().length() > 100) {
            throw new RuntimeException("Le nom du département ne doit pas dépasser 100 caractères");
        }
        departement.setNomDepartement(detailsDepartement.getNomDepartement());
        return departementRepository.save(departement);
    }

    public void deleteDepartement(int id) {
        Departement departement = departementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Département non trouvé"));
        departementRepository.delete(departement);
    }
}
