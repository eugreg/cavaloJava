package br.univille.poo2.cavalo.service;

import br.univille.poo2.cavalo.dto.CavaloGrid;
import br.univille.poo2.cavalo.dto.CavaloRecord;
import br.univille.poo2.cavalo.entity.CavaloEntity;
import br.univille.poo2.cavalo.mapper.CavaloMapper;
import br.univille.poo2.cavalo.repository.CavaloRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CavaloService {
    private CavaloMapper cavaloMapper;
    private CavaloRepository cavaloRepository;

    public CavaloEntity saveCavalo(CavaloRecord cavaloRecord){
        CavaloEntity cavaloEntity = cavaloMapper.toEntity(cavaloRecord);

        cavaloRepository.save(cavaloEntity);

        return cavaloEntity;
    }

    public List<CavaloGrid> listaTodosCavalos(){
        return cavaloRepository.listaTodosCavalos();
    }

    public CavaloRecord findByid(Long id){
        CavaloEntity cavaloEntity =  cavaloRepository.findById(id).orElseThrow(() -> new RuntimeException());

        return cavaloMapper.toDto(cavaloEntity);
    }

    public void deleteCavalo(Long id){
        CavaloEntity cavalo = cavaloRepository.findById(id).orElse(null);
        cavaloRepository.delete(cavalo);
    }

}
