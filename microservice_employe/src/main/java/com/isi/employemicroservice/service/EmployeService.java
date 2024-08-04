package com.isi.employemicroservice.service;

import com.isi.employemicroservice.client.Departement;
import com.isi.employemicroservice.client.DepartementRestClient;
import com.isi.employemicroservice.entity.Employe;
import com.isi.employemicroservice.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeService {

    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private DepartementRestClient departementRestClient;

    public Employe addEmploye(Employe employe) {
        if (employeRepository.findByEmail(employe.getEmail()).isPresent()) {
            throw new RuntimeException("L'email existe déjà");
        }
        if (!employe.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new RuntimeException("L'email n'est pas valide");
        }
        if (employe.getNom().length() > 100) {
            throw new RuntimeException("Le nom ne doit pas dépasser 100 caractères");
        }
        if (employe.getPrenom().length() > 250) {
            throw new RuntimeException("Le prénom ne doit pas dépasser 250 caractères");
        }
        if (employe.getAdresse().length() > 250) {
            throw new RuntimeException("L'adresse ne doit pas dépasser 250 caractères");
        }
        if (!employe.getTelephone().matches("^(\\+221|00221)(77|78|33|76)\\d{7}$")) {
            throw new RuntimeException("Le numéro de téléphone doit commencer par +221 ou 00221 et suivre le format correct");
        }
        if (employe.getDateNaissance().isAfter(LocalDate.of(2020, 1, 1))) {
            throw new RuntimeException("La date de naissance doit être avant 2020");
        }
        return employeRepository.save(employe);
    }

    public List<Employe> getAllEmployes() {
        List<Employe> employes = employeRepository.findAll();
        employes.forEach(employe -> {
            if (employe.getDepartementId() != 0) {
                Departement departement = departementRestClient.findDepartementById(employe.getDepartementId());
                employe.setDepartement(departement);
            }
        });
        return employes;
    }

    public Optional<Employe> getEmployeById(int id) {
        Optional<Employe> employeOptional = employeRepository.findById(id);
        if (employeOptional.isPresent()) {
            Employe employe = employeOptional.get();
            if (employe.getDepartementId() != 0) {
                Departement departement = departementRestClient.findDepartementById(employe.getDepartementId());
                employe.setDepartement(departement);
            }
        }
        return employeOptional;
    }

    public Employe assignDepartementToEmploye(int employeId, int departementId) {
        Employe employe = employeRepository.findById(employeId)
                .orElseThrow(() -> new RuntimeException("Employe not found"));

        Departement departement = departementRestClient.findDepartementById(departementId);
        if (departement == null) {
            throw new RuntimeException("Departement not found");
        }

        employe.setDepartementId(departementId);
        return employeRepository.save(employe);
    }

    public Optional<Employe> getEmployeByName(String nom) {
        return employeRepository.findByNom(nom);
    }

    public Optional<Employe> getEmployeByEmail(String email) {
        return employeRepository.findByEmail(email);
    }

    public Employe updateEmploye(int id, Employe detailsEmploye) {
        Employe employe = employeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé"));

        if (detailsEmploye.getNom().length() > 100) {
            throw new RuntimeException("Le nom ne doit pas dépasser 100 caractères");
        }
        if (detailsEmploye.getPrenom().length() > 250) {
            throw new RuntimeException("Le prénom ne doit pas dépasser 250 caractères");
        }
        if (detailsEmploye.getAdresse().length() > 250) {
            throw new RuntimeException("L'adresse ne doit pas dépasser 250 caractères");
        }
        if (!detailsEmploye.getTelephone().matches("^(\\+221|00221)(77|78|33|76)\\d{7}$")) {
            throw new RuntimeException("Le numéro de téléphone doit commencer par +221 ou 00221 et suivre le format correct");
        }
        if (!detailsEmploye.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new RuntimeException("L'email n'est pas valide");
        }
        if (detailsEmploye.getDateNaissance().isAfter(LocalDate.of(2020, 1, 1))) {
            throw new RuntimeException("La date de naissance doit être avant 2020");
        }

        employe.setNom(detailsEmploye.getNom());
        employe.setPrenom(detailsEmploye.getPrenom());
        employe.setEmail(detailsEmploye.getEmail());
        employe.setAdresse(detailsEmploye.getAdresse());
        employe.setDateNaissance(detailsEmploye.getDateNaissance());
        employe.setTelephone(detailsEmploye.getTelephone());

        return employeRepository.save(employe);
    }

    public void deleteEmploye(int id) {
        Employe employe = employeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé"));
        employeRepository.delete(employe);
    }
}
