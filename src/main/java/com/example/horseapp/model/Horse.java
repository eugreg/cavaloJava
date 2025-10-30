package com.example.horseapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "horses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Horse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String raca;
    private Integer idade;
    private String cor;

    @Column(length = 2000)
    private String descricao;
}
