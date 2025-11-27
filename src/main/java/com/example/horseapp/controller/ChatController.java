package com.example.horseapp.controller;

import com.example.horseapp.service.ChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/horses")
public class ChatController {  // <- corrigi aqui

    private final ChatService chatService;

    public ChatController(ChatService chatService) {  // <- constructor deve ter mesmo nome da classe
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String mensagem) throws Exception {
        return chatService.chat(mensagem);
    }
}
