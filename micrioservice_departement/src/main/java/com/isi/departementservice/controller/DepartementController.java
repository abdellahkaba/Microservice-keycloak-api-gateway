package com.isi.departementservice.controller;

;
import com.isi.departementservice.entity.Departement;
import com.isi.departementservice.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/departements")
public class DepartementController {

    @Autowired
    private DepartementService departementService;

    @GetMapping
    public ResponseEntity<?> getAllDepartements() {
        List<Departement> departements = departementService.getAllDepartements();

        if (departements.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Il n'y a pas de départements pour le moment.");
        }
        return ResponseEntity.ok(departements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getDepartementById(@PathVariable int id) {
        Optional<Departement> departement = departementService.getDepartementById(id);

        return departement.map(value ->
                        ResponseEntity.ok().body("Département trouvé ." + "ID:" + value.getIdDepartement() + " Nom:" + value.getNomDepartement()))
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("Le département avec l'ID " + id + " n'existe pas."));
    }


    @GetMapping("/nom/{nom}")
    public ResponseEntity<String> getDepartementByNom(@PathVariable String nom) {
        Optional<Departement> departement = departementService.getDepartementByName(nom);
        return departement.map(value -> ResponseEntity.ok().body(value.toString()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Le département avec ce nom n'existe pas."));
    }

    @PostMapping
    public ResponseEntity<String> createDepartement(@RequestBody Departement departement) {
        try {
            Departement createdDepartement = departementService.addDepartement(departement);
            return ResponseEntity.status(HttpStatus.CREATED).body("Département créé avec succès : " + createdDepartement.getNomDepartement());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDepartement(@PathVariable int id, @RequestBody Departement departement) {
        try {
            Departement updatedDepartement = departementService.updateDepartement(id, departement);
            return ResponseEntity.ok().body("Département mis à jour avec succès." + " ID:" + id  + " Nom:" + updatedDepartement.getNomDepartement());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartement(@PathVariable int id) {
        try {
            departementService.deleteDepartement(id);
            return ResponseEntity.ok().body("Département supprimé avec succès." + "ID:" + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}