package com.isi.employe.employe;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final EmployeService service ;
    @PostMapping
    public ResponseEntity<String> createEmploye(
            @RequestBody @Valid EmployeRequest request){
        return ResponseEntity.ok(service.createEmploye(request));
    }
//    @GetMapping
//    public ResponseEntity<List<EmployeResponse>> findAll() {
//        return ResponseEntity.ok(service.findAllEmploye());
//    }
    @GetMapping
    public ResponseEntity<List<EmployeResponse1>> findAll(){
        return ResponseEntity.ok(service.findAl());
    }
    @PutMapping("/{employe-id}")
    public ResponseEntity<Void> updateEmploye(@PathVariable("employe-id") String id, @RequestBody @Valid UpdateEmployeRequest request) {
        request = new UpdateEmployeRequest(id,
                request.prenom(),
                request.nom(),
                request.email(),
                request.adresse(),
                request.dateNaissance(),
                request.tel(), request.statut(),
                request.departementId());
        service.updateEmploye(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/exists/{employe-id}")
    public ResponseEntity<Boolean> existById(
            @PathVariable("employe-id") String employeId
    ){
        return ResponseEntity.ok(service.existById(employeId));
    }


    @GetMapping("/{employe-id}")
    public ResponseEntity<EmployeResponse1> findById(
            @PathVariable("employe-id") String employeId
    ){
        return ResponseEntity.ok(service.findById(employeId));
    }

    @DeleteMapping("/{employe-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("employe-id") String employeId
    ){
        service.deleteEmploye(employeId);
        return ResponseEntity.accepted().build();
    }
    

}
