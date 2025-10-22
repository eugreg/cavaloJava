package br.univille.poo2.cavalo.mapper;


import br.univille.poo2.cavalo.dto.CavaloRecord;
import br.univille.poo2.cavalo.entity.CavaloEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CavaloMapper {

    CavaloEntity toEntity(CavaloRecord record);

    CavaloRecord toDto(CavaloEntity entity);
}
