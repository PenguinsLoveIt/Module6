package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class JSONPlaceholderFetcher {
    private final String URL_ADDRESS = "https://jsonplaceholder.typicode.com/posts";
    private final HttpClient client = HttpClient.newHttpClient();
    private JSONMapper jsonMapper = new JSONMapper();

    public Post getSinglePost(int id) {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(URL_ADDRESS + "/" + id)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode() == 201 || response.statusCode() == 200 ? jsonMapper.mapTo(response.body()) : null;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Post> getAllPostObjects() {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(URL_ADDRESS)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return jsonMapper.mapToList(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAllPosts() {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(URL_ADDRESS)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
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
