package com.example.horseapp.controller;

import com.example.horseapp.model.Horse;
import com.example.horseapp.repository.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/horses")
public class HorseController {

    @Autowired
    private HorseRepository horseRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("horses", horseRepository.findAll());
        return "horses/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("horse", new Horse());
        return "horses/form";
    }

    @PostMapping
    public String save(@ModelAttribute Horse horse) {
        horseRepository.save(horse);
        return "redirect:/horses";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        Horse horse = horseRepository.findById(id).orElseThrow();
        model.addAttribute("horse", horse);
        return "horses/view";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Horse horse = horseRepository.findById(id).orElseThrow();
        model.addAttribute("horse", horse);
        return "horses/form";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        horseRepository.deleteById(id);
        return "redirect:/horses";
    }
}
