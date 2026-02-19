package com.aluracursos.literalura.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String getData(String url) {
        HttpClient httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try{
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() != 200){
                System.out.println("Atención: La API devolvió un código de estado " + response.statusCode());
            }
        } catch(Exception e){
            throw new RuntimeException("Fallo al consumir la API: " + e.getMessage());
        }
        String json = response.body();
        return json;
    }
}
