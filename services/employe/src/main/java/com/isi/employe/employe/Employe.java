package com.isi.employe.employe;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Employe {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String prenom;
    private String nom;
    private String email;
    private String adresse;
    private LocalDate dateNaissance;
    private String tel;
    @Enumerated(EnumType.STRING)
    private Statut statut;
    private Integer departementId;
    private Integer postId;

}
