package com.isi.departementservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "departements_tb")
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDepartement;

    @Column(name = "nomDepartement" , length = 100, unique = true,nullable = false)
    private String nomDepartement;

    /*
    @OneToMany(mappedBy = "departement")
    private List<Employe> employes;

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }
     */
}
