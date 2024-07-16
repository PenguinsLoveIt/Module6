package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class JSONPlaceholderFetcher {
    private final String URL_ADDRESS = "https://jsonplaceholder.typicode.com/users";
    private final HttpClient client = HttpClient.newHttpClient();

    public String getSinglePost(int id) {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(URL_ADDRESS + "/" + id)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode() == 201 || response.statusCode() == 200 ? response.body() : "Invalid ID";
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAllPosts() {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(URL_ADDRESS)).GET().build();
            CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            return response.join().body();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addPost(String post) {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(URL_ADDRESS))
                    .POST(HttpRequest.BodyPublishers.ofString(post)).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 201 || response.statusCode() == 200;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
