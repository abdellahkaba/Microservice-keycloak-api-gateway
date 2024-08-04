package com.isi.departementservice.repository;

import com.isi.departementservice.entity.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Integer> {
    Optional<Departement> findByNomDepartement(String nomDepartement);

    @Query(
            nativeQuery = true,
            value =
                     "SELECT ed.idDepartement, ed.nomDepartement FROM departementdb ed join employedb e on e.id = ed.employe_id where ed.employe_id=:employeId")
    Optional<Departement> findDepartementByEmployeId(@Param("employeId") int employeId);
}