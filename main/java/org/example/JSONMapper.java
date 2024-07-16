package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class JSONMapper {
    private final ObjectMapper mapper = new ObjectMapper();
    private final HttpClient client = HttpClient.newHttpClient();
    private final String URL_ADDRESS = "https://jsonplaceholder.typicode.com/users";

    public Post mapTo(String JSON) {
        JsonNode node;
        try {
            node = mapper.readTree(JSON);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new Post(
                node.get("userID").asLong(),
                node.get("id").asLong(),
                node.get("title").asText(),
                node.get("body").asText()
        );
    }

    public Post getSinglePost(String json) {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(URL_ADDRESS + "/" + json)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 201 || response.statusCode() == 200 ? mapper.readValue(json, Post.class) : null;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Post> getAllPosts(String json) {
        List<Post> posts = new ArrayList<>();
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(URL_ADDRESS)).GET().build();
            CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(posts);

            return response.join().statusCode() == 201 || response.join().statusCode() == 200 ?
                    mapper.readValue(result, new TypeReference<>() {}) : null;
        } catch (URISyntaxException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String mapToJSON(Post post) {
        try {
            return mapper.writeValueAsString(post);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
