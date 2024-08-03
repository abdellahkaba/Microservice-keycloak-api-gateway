package com.isi.departement.departement;


import com.isi.departement.exception.DepartementNotFoundException;
import com.isi.departement.exception.NomConflictException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartementService {

    public final DepartementRepository repository;
    public final DepartementMapper mapper ;

    public Integer addDepartement(DepartementRequest request) {
        if(repository.findByNom(request.nom()).isPresent()){
            throw new NomConflictException("Le nom de departement existe deja");
        }
        var departement = repository.save(mapper.toDepartement(request));
        return departement.getId();
    }

    public List<DepartementResponse> findAllDepartement() {
        return repository.findAll()
                .stream()
                .map(mapper::fromDepartement)
                .collect(Collectors.toList());
    }

    public void updateDepartement(UpdateDepartementRequest request) {
        var departement = repository.findById(request.id())
                .orElseThrow(() -> new DepartementNotFoundException(
                        String.format("Le departement non trouvé ID:: %s", request.id())
                ));
        mergeDepartement(departement,request);
        repository.save(departement);
    }

    private void mergeDepartement(Departement departement, UpdateDepartementRequest request){
        if (StringUtils.isNotBlank(request.nom()) &&
            !request.nom().equals(departement.getNom()) &&
                repository.findByNom(request.nom()).isPresent()){
            throw new NomConflictException("Le nom existe deja !");
        }
        if(StringUtils.isNotBlank(request.nom())){
            departement.setNom(request.nom());
        }
        if (StringUtils.isNotBlank(request.description())){
            departement.setDescription(request.description());
        }
    }

    public Boolean existById(Integer departementId) {
        return repository.findById(departementId)
                .isPresent();
    }

    public DepartementResponse findById(Integer departementId) {
        return repository.findById(departementId)
                .map(mapper::fromDepartement)
                .orElseThrow(() -> new DepartementNotFoundException(
                        String.format("Le departement non trouvé")
                ));
    }
    public void deleteDepartement(Integer departementId) {
        if (!repository.existsById(departementId)) {
            throw new DepartementNotFoundException(
                    String.format("Le departement non trouvé ID:: %s", departementId)
            );
        }
        repository.deleteById(departementId);
    }
}
