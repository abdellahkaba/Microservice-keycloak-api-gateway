package com.example.gestionrh.services;

import com.example.gestionrh.entities.GestionnaireRH;
import com.example.gestionrh.exceptions.EmailAlreadyExistsException;
import com.example.gestionrh.exceptions.GestionnaireRHNotFoundException;
import com.example.gestionrh.exceptions.PhoneAlreadyExistsException;
import com.example.gestionrh.repository.GestionRHRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour la gestion des entités GestionnaireRH.
 */
@Service
@AllArgsConstructor
public class GestionRHService {
    private GestionRHRepository gestionRHRepository;

    /**
     * Sauvegarde un nouveau GestionnaireRH.
     * Vérifie si l'email ou le téléphone existe déjà avant de sauvegarder.
     *
     * @param gestionnaireRH l'entité GestionnaireRH à sauvegarder
     * @return l'entité sauvegardée
     */
    public GestionnaireRH saveGestionnaire(GestionnaireRH gestionnaireRH) {
        if (gestionRHRepository.existsByEmail(gestionnaireRH.getEmail())) {
            throw new EmailAlreadyExistsException("Email existe dejà");
        }
        if (gestionRHRepository.existsByPhone(gestionnaireRH.getPhone())) {
            throw new PhoneAlreadyExistsException("Phone existe dejà");
        }
        return gestionRHRepository.save(gestionnaireRH);
    }

    /**
     * Récupère tous les GestionnaireRH.
     *
     * @return une liste de toutes les entités GestionnaireRH
     */
    public List<GestionnaireRH> getAll() {
        return gestionRHRepository.findAll();
    }

    /**
     * Récupère un GestionnaireRH par son ID.
     *
     * @param id l'identifiant du GestionnaireRH
     * @return l'entité GestionnaireRH trouvée
     * @throws GestionnaireRHNotFoundException si aucun GestionnaireRH n'est trouvé avec cet ID
     */
    public GestionnaireRH getOne(int id) throws GestionnaireRHNotFoundException {
        return gestionRHRepository.findById(id)
                .orElseThrow(() -> new GestionnaireRHNotFoundException("Gestionnaire avec id " + id + " non trouvé"));
    }

    /**
     * Supprime un GestionnaireRH par son ID.
     *
     * @param id l'identifiant du GestionnaireRH à supprimer
     */
    public void deleteRH(int id) throws GestionnaireRHNotFoundException {
        if (!gestionRHRepository.existsById(id)) {
            throw new GestionnaireRHNotFoundException("Gestionnaire with id " + id + " not found");
        }
        gestionRHRepository.deleteById(id);
    }

    /**
     * Met à jour un GestionnaireRH existant.
     * Vérifie si l'email ou le téléphone existe déjà avant de sauvegarder.
     * Met à jour seulement les champs non nuls.
     *
     * @param id     l'identifiant du GestionnaireRH à mettre à jour
     * @param detail les nouvelles valeurs pour l'entité GestionnaireRH
     * @return l'entité mise à jour
     * @throws GestionnaireRHNotFoundException si aucun GestionnaireRH n'est trouvé avec cet ID
     */
    public GestionnaireRH updateRH(int id, GestionnaireRH detail) throws GestionnaireRHNotFoundException {
        GestionnaireRH gestionnaireRH = gestionRHRepository.findById(id)
                .orElseThrow(() -> new GestionnaireRHNotFoundException("Gestionnaire non trouvé" + id + " non trouvé"));

        if (!gestionnaireRH.getEmail().equals(detail.getEmail()) &&
                gestionRHRepository.existsByEmail(detail.getEmail())) {
            throw new EmailAlreadyExistsException("Email existe dejà");
        }

        if (!gestionnaireRH.getPhone().equals(detail.getPhone()) &&
                gestionRHRepository.existsByPhone(detail.getPhone())) {
            throw new PhoneAlreadyExistsException("Phone existe dejà");
        }

        // Mise à jour des champs seulement si les nouvelles valeurs ne sont pas nulles
        if (detail.getPrenom() != null) {
            gestionnaireRH.setPrenom(detail.getPrenom());
        }
        if (detail.getNom() != null) {
            gestionnaireRH.setNom(detail.getNom());
        }
        if (detail.getEmail() != null) {
            gestionnaireRH.setEmail(detail.getEmail());
        }
        if (detail.getPhone() != null) {
            gestionnaireRH.setPhone(detail.getPhone());
        }

        return gestionRHRepository.save(gestionnaireRH);
    }
}
