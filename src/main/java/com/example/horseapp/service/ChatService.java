package com.example.horseapp.service;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final GeminiService geminiService;

    public ChatService(GeminiService geminiService) {
        this.geminiService = geminiService;
    }


        public String chat(String prompt) throws Exception {

            String resposta = geminiService.gerarTexto(prompt);

            return resposta;
        }
}
