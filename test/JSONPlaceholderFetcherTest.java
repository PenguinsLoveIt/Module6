import org.example.JSONPlaceholderFetcher;
import org.example.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;

public class JSONPlaceholderFetcherTest {
    private final String URL_ADDRESS = "https://jsonplaceholder.typicode.com/posts";
    private final HttpClient client = HttpClient.newHttpClient();

    JSONPlaceholderFetcher testObject = new JSONPlaceholderFetcher();

    @Test
    public void checkIfJsonMatchesId() {
        String Json = "{userID=1, id=1, title='sunt aut facere repellat provident occaecati excepturi optio reprehenderit', body='quia et suscipit\n" +
                "suscipit recusandae consequuntur expedita et cum\n" +
                "reprehenderit molestiae ut ut quas totam\n" +
                "nostrum rerum est autem sunt rem eveniet architecto'}";

        Post singlePost = testObject.getSinglePost(1);
        Assertions.assertEquals(singlePost.getBody(), Json);
    }

    @Test
    public void checkIfJsonDoesNotMatchId() {
        String json = "incorrect json";

        Post singlePost = testObject.getSinglePost(1);
        Assertions.assertNotEquals(singlePost.getBody(), json);
    }

    @Test
    public void checkIfArraySizeIsEqual() {
        int size = testObject.getAllPostObjects().size();
        Assertions.assertEquals(size, 100);
    }
}
