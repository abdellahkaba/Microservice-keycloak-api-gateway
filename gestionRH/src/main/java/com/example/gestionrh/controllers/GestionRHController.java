package com.example.gestionrh.controllers;

import com.example.gestionrh.entities.GestionnaireRH;
import com.example.gestionrh.exceptions.EmailAlreadyExistsException;
import com.example.gestionrh.exceptions.GestionnaireRHNotFoundException;
import com.example.gestionrh.exceptions.PhoneAlreadyExistsException;
import com.example.gestionrh.services.GestionRHService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des entités GestionnaireRH.
 */
@RestController
@RequestMapping("/api/v1")
public class GestionRHController {

    private final GestionRHService gestionRHService;

    /**
     * Constructeur pour injecter le service GestionRHService.
     *
     * @param gestionRHService le service GestionRHService à injecter
     */
    @Autowired
    public GestionRHController(GestionRHService gestionRHService) {
        this.gestionRHService = gestionRHService;
    }

    /**
     * Endpoint pour sauvegarder un nouveau GestionnaireRH.
     *
     * @param gestionnaireRH l'entité GestionnaireRH à sauvegarder
     * @return l'entité sauvegardée
     */
    @PostMapping("/rh")
    public ResponseEntity<?> saveGestionnaire(@RequestBody GestionnaireRH gestionnaireRH) {
        try {
            GestionnaireRH createdGestionnaire = gestionRHService.saveGestionnaire(gestionnaireRH);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGestionnaire);
        } catch (EmailAlreadyExistsException | PhoneAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unexpected error occurred");
        }
    }

    /**
     * Endpoint pour récupérer tous les GestionnaireRH.
     *
     * @return une liste de toutes les entités GestionnaireRH
     */
    @GetMapping("/rh")
    public List<GestionnaireRH> getAll() {
        return gestionRHService.getAll();
    }

    /**
     * Endpoint pour récupérer un GestionnaireRH par son ID.
     *
     * @param id l'identifiant du GestionnaireRH
     * @return l'entité GestionnaireRH trouvée
     * @throws GestionnaireRHNotFoundException si aucun GestionnaireRH n'est trouvé avec cet ID
     */
    @GetMapping("/rh/{id}")
    public GestionnaireRH getOne(@PathVariable(name = "id") int id) throws GestionnaireRHNotFoundException {
        return gestionRHService.getOne(id);
    }

    /**
     * Endpoint pour supprimer un GestionnaireRH par son ID.
     *
     * @param id l'identifiant du GestionnaireRH à supprimer
     */
    @DeleteMapping("/rh/{id}")
    public ResponseEntity<String> deleteRh(@PathVariable int id) {
        try {
            gestionRHService.deleteRH(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Gestionnaire supprimé avec succèss");
        } catch (GestionnaireRHNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint pour mettre à jour un GestionnaireRH existant.
     *
     * @param id l'identifiant du GestionnaireRH à mettre à jour
     * @param gestionnaireRH les nouvelles valeurs pour l'entité GestionnaireRH
     * @return l'entité mise à jour
     * @throws GestionnaireRHNotFoundException si aucun GestionnaireRH n'est trouvé avec cet ID
     */
    @PutMapping("/rh/{id}")
    public ResponseEntity<?> updateRH(@PathVariable int id, @RequestBody GestionnaireRH gestionnaireRH) {
        try {
            GestionnaireRH updatedGestionnaireRH = gestionRHService.updateRH(id, gestionnaireRH);
            return ResponseEntity.ok(updatedGestionnaireRH);
        } catch (GestionnaireRHNotFoundException | EmailAlreadyExistsException | PhoneAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
