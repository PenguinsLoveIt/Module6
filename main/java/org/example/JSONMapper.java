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


    public Post mapTo(String JSON) {
        JsonNode node;
        try {
            node = mapper.readTree(JSON);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new Post(
                node.get("userId").asLong(),
                node.get("id").asLong(),
                node.get("title").asText(),
                node.get("body").asText()
        );
    }

    public List<Post> mapToList(String JSON) {
        List<Post> posts = new ArrayList<>();
        JsonNode node;

        try {
            node = mapper.readTree(JSON);
            for (int i = 0; i < node.size(); i++) {
                posts.add(new Post(node.get(i).get("userId").asLong(),
                        node.get(i).get("id").asLong(),
                        node.get(i).get("title").asText(),
                        node.get(i).get("body").asText()));
            }
            return posts;
        } catch (JsonProcessingException e) {
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
