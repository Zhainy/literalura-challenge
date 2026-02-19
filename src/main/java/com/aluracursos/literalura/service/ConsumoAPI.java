package com.aluracursos.literalura.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String getData(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try{
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch(Exception e){
            throw new RuntimeException(e);
        }
        String json = response.body();
        return json;
    }
}
