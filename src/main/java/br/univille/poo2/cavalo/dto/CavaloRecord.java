package br.univille.poo2.cavalo.dto;

import java.time.LocalDate;

public record CavaloRecord(
        Long id,
        String nome,
        String raca,
        Integer peso,
        String sexo,
        String cuido,
        String pelagem,
        Long idade,
        LocalDate dataNascimento,
        String foto
) {
}
