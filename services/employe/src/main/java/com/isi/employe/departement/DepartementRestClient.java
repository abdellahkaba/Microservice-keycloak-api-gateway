package com.isi.employe.departement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "departement-service",
        url = "${application.config.departement-url}"
)
public interface DepartementRestClient {
    @GetMapping("/{departement-id}")
    Optional<DepartementResponse> findDepartementById(
            @PathVariable("departement-id") Integer departementId
    );
//    @GetMapping
//    Optional<DepartementResponse> getAllDepartement();
}
