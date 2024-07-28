package com.isi.departement.departement;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departements")
public class DepartementController {
    public final DepartementService service ;
    @PostMapping
    public ResponseEntity<Integer> addDepartement(
            @RequestBody @Valid DepartementRequest request
    ){
        return ResponseEntity.ok(service.addDepartement(request));
    }
    @GetMapping
    public ResponseEntity<List<DepartementResponse>> findAll(){
        return ResponseEntity.ok(service.findAllDepartement());
    }

    @PutMapping
    public ResponseEntity<Void> updateDepartement(
            @RequestBody @Valid DepartementRequest request
    ){
        service.updateDepartement(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/exists/{departement-id}")
    public ResponseEntity<Boolean> existById(
            @PathVariable("departement-id") Integer departementId
    ){
        return ResponseEntity.ok(service.existById(departementId));
    }
    @GetMapping("/{departement-id}")
    public ResponseEntity<DepartementResponse> findById(
            @PathVariable("departement-id") Integer departementId
    ){
        return ResponseEntity.ok(service.findById(departementId));
    }
    @DeleteMapping("/{departement-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("departement-id") Integer departementId
    ){
        service.deleteDepartement(departementId);
        return ResponseEntity.accepted().build();
    }
}
