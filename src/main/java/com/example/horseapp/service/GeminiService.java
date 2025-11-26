package com.example.horseapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

@Service
public class GeminiService {

    private final String apiKey;
    private final HttpClient http;

    public GeminiService(@Value("${gemini.api.key}") String apiKey) {
        this.apiKey = apiKey;
        this.http = HttpClient.newHttpClient();
    }

    public String gerarContexto() throws Exception {
        String url = "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent?key=" + apiKey;

        String body = """
        {
          "contents": [{
            "parts": [{
              "text": "Voce irar responder questoes sobre cavalo e somente isso responda com um Ok e somente isso"
            }]
          }]
        }
        """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String gerarTexto(String prompt) throws Exception {
        String context = gerarContexto();

        if ("Ok".indexOf(context) == 0) {
            String url = "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent?key=" + apiKey;

            String body = """
                    {
                      "contents": [{
                        "parts": [{
                          "text": " %s"
                        }]
                      }]
                    }
                    """.formatted(prompt);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } else {
            return "erro";
        }
    }
}
