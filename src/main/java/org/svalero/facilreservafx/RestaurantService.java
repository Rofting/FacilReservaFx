package org.svalero.facilreservafx;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RestaurantService {
    private static final String API_URL = "http://localhost:8080/restaurants";
    private final HttpClient client = HttpClient.newHttpClient();

    public CompletableFuture<List<RestaurantFx>> fetchRestaurants() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(response.body(), new TypeReference<List<RestaurantFx>>() {});
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }
}
