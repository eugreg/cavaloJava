package com.example.horseapp.controller;

import com.example.horseapp.model.Horse;
import com.example.horseapp.repository.HorseRepository;
import com.example.horseapp.service.ChatService;
import com.example.horseapp.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/horses")
public class HorseController {


    @GetMapping("/chat/tela")
    public String goChat(){
        return "/horses/chat";
    }

}
