package com.isi.employe.employe;


import com.isi.employe.departement.DepartementResponse;
import com.isi.employe.departement.DepartementRestClient;
import com.isi.employe.exception.EmailConflictException;
import com.isi.employe.exception.EmployeNotFoundException;
import com.isi.employe.exception.TelConflictException;
import com.isi.employe.post.PostRestClient;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeService {

    private final EmployeRepository repository;
    private final EmployeMapper mapper ;
    private final DepartementRestClient restClient;
    private final PostRestClient postRestClient ;
    public String createEmploye(EmployeRequest request) {
        var departement = this.restClient.findDepartementById(
                request.departementId())
                .orElseThrow(() -> new EmployeNotFoundException(
                        "Il faut attribuer un employé à un departement"
                ));
        var post = this.postRestClient.findPostById(
                request.postId())
                .orElseThrow(() -> new EmployeNotFoundException(
                        "Il faut attribuer un employé à un poste"
                ));
        if (repository.findByEmail(request.email()).isPresent()){
           throw new EmailConflictException("L'email existe deja !");
        }
        if (departement==null){
            throw new EmployeNotFoundException("Il faut preciser le departement");
        }
        if (post == null){
            throw new EmployeNotFoundException("Il faut preciser le post");
        }
        if (repository.findByTel(request.tel()).isPresent()){
            throw new TelConflictException("Ce numero de Téléphone existe dejà");
        }

        var employe = this.repository.save(mapper.toEmploye(request));
        return employe.getId();
    }

public List<EmployeResponse> findAllEmploye() {
    return repository.findAll()
            .stream()
            .map(employe -> {
                var departement = restClient.findDepartementById(employe.getDepartementId())
                        .orElseThrow(() -> new EmployeNotFoundException(
                                "Département non trouvé pour l'employé"
                        ));
                return mapper.fromEmployeWithDepartement(employe, departement);
            })
            .collect(Collectors.toList());
}

    public void updateEmploye(UpdateEmployeRequest request) {
        var employe = repository.findById(request.id())
                .orElseThrow(() -> new EmployeNotFoundException(
                        String.format("Impossible de modifier l'employé:: Non trouvé ID:: %s", request.id())
                ));
        mergeEmploye(employe, request);
        repository.save(employe);
    }

    private void mergeEmploye(Employe employe, UpdateEmployeRequest request) {
        if (StringUtils.isNotBlank(request.email()) &&
                !request.email().equals(employe.getEmail()) &&
                repository.findByEmail(request.email()).isPresent()) {
            throw new EmailConflictException("L'email existe déjà");
        }
        if (StringUtils.isNotBlank(request.tel()) &&
                !request.tel().equals(employe.getTel()) &&
                repository.findByTel(request.tel()).isPresent()) {
            throw new TelConflictException("Ce numéro de Téléphone existe déjà");
        }

        if (StringUtils.isNotBlank(request.prenom())) {
            employe.setPrenom(request.prenom());
        }
        if (StringUtils.isNotBlank(request.nom())) {
            employe.setNom(request.nom());
        }
        if (StringUtils.isNotBlank(request.email())) {
            employe.setEmail(request.email());
        }
        if (StringUtils.isNotBlank(request.adresse())) {
            employe.setAdresse(request.adresse());
        }
        if (request.dateNaissance() != null) {
            employe.setDateNaissance(request.dateNaissance());
        }
        if (StringUtils.isNotBlank(request.tel())) {
            employe.setTel(request.tel());
        }
        if (request.statut() != null) {
            employe.setStatut(request.statut());
        }
        if (request.departementId() != null){
            employe.setDepartementId(request.departementId());
        }
        if (request.postId() != null) {
            employe.setPostId(request.postId());
        }
        /*if (request.departementId() != null) {
            var departement = restClient.findDepartementById(request.departementId())
                    .orElseThrow(() -> new EmployeNotFoundException(
                            "Département non trouvé pour l'employé"
                    ));
            employe.setDepartementId(request.departementId());
        }*/
    }
    public Boolean existById(String employeId){
        return repository.findById(employeId)
                .isPresent();
    }
public EmployeResponse1 findById(String employeId) {
    var employe = repository.findById(employeId)
            .map(mapper::fromEmploye)
            .orElseThrow(() -> new EmployeNotFoundException("Département non trouvé pour l'employé"));
//    var departement = restClient.findDepartementById(employe.getDepartementId())
//            .orElseThrow(() -> new EmployeNotFoundException(
//                    "Département non trouvé pour l'employé"
//            ));
    return employe ;
}
    public void deleteEmploye(String employeId){
        repository.deleteById(employeId);
    }
    public List<EmployeResponse1> findAl () {
        return repository.findAll()
                .stream()
                .map(mapper::fromEmploye)
                .collect(Collectors.toList());
    }
}
