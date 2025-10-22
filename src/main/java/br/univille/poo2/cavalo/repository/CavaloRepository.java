package br.univille.poo2.cavalo.repository;

import br.univille.poo2.cavalo.dto.CavaloGrid;
import br.univille.poo2.cavalo.entity.CavaloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por manipular os dados do banco de dados
 * Consultas (com filtros), inserções/updates e deleção
 */
public interface CavaloRepository extends JpaRepository<CavaloEntity,Long> {
    @Query("SELECT new br.univille.poo2.cavalo.dto.CavaloGrid(c.id, c.foto," +
            "c.raca, c.nome, c.idade ) " +
            "FROM CavaloEntity c")
    List<CavaloGrid> listaTodosCavalos();

    Optional<CavaloEntity> findById(Long id);
}
