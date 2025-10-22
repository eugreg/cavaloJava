package br.univille.poo2.cavalo.controller;

import br.univille.poo2.cavalo.dto.CavaloGrid;
import br.univille.poo2.cavalo.dto.CavaloRecord;
import br.univille.poo2.cavalo.entity.CavaloEntity;
import br.univille.poo2.cavalo.service.CavaloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cavalo")
public class CavaloController {
    private CavaloService cavaloService;

    @GetMapping("/list")
        public List<CavaloGrid> list(){
        return cavaloService.listaTodosCavalos();
    }

    @DeleteMapping("/{id}")
    public void deleteCavalo(@PathVariable Long id){
        cavaloService.deleteCavalo(id);
    }

    @PostMapping("/save")
        public CavaloEntity saveCavalo(CavaloRecord cavaloRecord){
          return   cavaloService.saveCavalo(cavaloRecord);
        }

    @GetMapping("/{id}")
        public CavaloRecord findById(Long id){
            return cavaloService.findByid(id);

    }


}
