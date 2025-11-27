package com.example.horseapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GeminiService {

    private final String apiKey;
    private final HttpClient http;
    private final ObjectMapper objectMapper; // Usado para criar e ler JSON corretamente

    // URL correta para o modelo flash 1.5 (2.5 ainda não é padrão público estável, ajustado para 1.5)
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent";

    public GeminiService(@Value("${gemini.api.key}") String apiKey, ObjectMapper objectMapper) {
        this.apiKey = apiKey;
        this.http = HttpClient.newHttpClient();
        this.objectMapper = objectMapper;
    }

    public String gerarTexto(String promptUsuario) {
        try {
            // 1. Construção do JSON de forma segura usando Jackson
            ObjectNode rootNode = objectMapper.createObjectNode();

            // A) Adiciona a instrução do sistema (O "Contexto")
            ObjectNode systemInstruction = rootNode.putObject("system_instruction");
            systemInstruction.putObject("parts").put("text", "Você é um assistente especializado em cavalos. Responda apenas perguntas relacionadas a cavalos. Se o assunto não for cavalos, diga que não pode responder.");

            // B) Adiciona a pergunta do usuário
            ArrayNode contents = rootNode.putArray("contents");
            ObjectNode userMessage = contents.addObject();
            userMessage.put("role", "user");
            userMessage.putArray("parts").addObject().put("text", promptUsuario);

            String jsonBody = objectMapper.writeValueAsString(rootNode);

            // 2. Preparação da Requisição HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + "?key=" + apiKey)) // Passando a chave na URL ou Header
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            // 3. Envio e recebimento
            HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());

            // 4. Tratamento da resposta
            if (response.statusCode() == 200) {
                return extrairTextoDaResposta(response.body());
            } else {
                return "Erro na API: " + response.statusCode() + " - " + response.body();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro interno ao processar a solicitação.";
        }
    }

    // Método auxiliar para pegar apenas o texto "limpo" do JSON complexo do Gemini
    private String extrairTextoDaResposta(String jsonResponse) {
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);
            // Caminho: candidates[0] -> content -> parts[0] -> text
            JsonNode textNode = root.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text");

            return textNode.asText();
        } catch (Exception e) {
            return "Erro ao ler resposta JSON.";
        }
    }
}