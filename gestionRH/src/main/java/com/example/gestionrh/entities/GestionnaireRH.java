package com.example.gestionrh.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GestionnaireRH {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String prenom;
    @Column(nullable = false, length = 100)
    private String nom;
    @Column(nullable = false, length = 250, unique = true)
    private String email;
    @Column(nullable = false, length = 100, unique = true)
    private String phone;
}
