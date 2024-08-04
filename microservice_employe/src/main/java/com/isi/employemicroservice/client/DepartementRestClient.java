package com.isi.employemicroservice.client;



import com.isi.employemicroservice.config.FeignConfig;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "departementService", configuration = FeignConfig.class)
public interface DepartementRestClient {


    @GetMapping("/departements/{id}")
    //cette interface permet de chercher un departement
    @CircuitBreaker(name = "departementService", fallbackMethod = "getDefaultDepartement")
    Departement findDepartementById(@PathVariable int id);

    //une methode qui retourne la liste de departement
    @CircuitBreaker(name = "departementService", fallbackMethod = "getAllDepartement")
    @GetMapping("/departements")
    List<Departement> allDepartements();
    default Departement getDefaultDepartement (int id, Exception exception) {
        Departement departement = new Departement() ;
        departement.setIdDepartement(id);
        departement.setNomDepartement(departement.getNomDepartement());
        return departement ;

    }

    default List<Departement> getAllDepartement (Exception exception) {
        return  List.of();
    }

}



