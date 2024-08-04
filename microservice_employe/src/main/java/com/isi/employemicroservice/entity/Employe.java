package com.isi.employemicroservice.entity;

import com.isi.employemicroservice.client.Departement;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "employes_tb")
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom_employe", nullable = false, length = 100)
    private String nom;

    @Column(name = "prenom_employe", nullable = false, length = 250)
    private String prenom;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "adresse_employe", nullable = false, length = 250)
    private String adresse;

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(name = "telephone_employe", nullable = false, length = 15,unique = true)
    private String telephone;
    @Transient
    private Departement departement ;
    private int departementId ;

    /*
    @ManyToOne
    @JoinColumn(name = "idDepartement")
    private Departement departement;

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
     */

}

