package org.example;

import java.net.http.HttpResponse;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        JSONPlaceholderFetcher fetcher = new JSONPlaceholderFetcher();

        Post singlePost = fetcher.getSinglePost(2);
        System.out.println(fetcher.getSinglePost(1));
        List<Post> allPostObjects = fetcher.getAllPostObjects();
//        System.out.println(fetcher.getAllPosts());
//        System.out.println(fetcher.getAllPostObjects());


        JSONMapper mapper = new JSONMapper();
//        System.out.println(mapper.mapToList(fetcher.getAllPosts()));
        System.out.println(mapper.mapToJSON(singlePost));
//        boolean b = fetcher.addPost("{\n" +
//                "    \"id\": 7,\n" +
//                "    \"name\": \"Kurtis Weissnat\",\n" +
//                "    \"username\": \"Elwyn.Skiles\",\n" +
//                "    \"email\": \"Telly.Hoeger@billy.biz\",\n" +
//                "    \"address\": {\n" +
//                "      \"street\": \"Rex Trail\",\n" +
//                "      \"suite\": \"Suite 280\",\n" +
//                "      \"city\": \"Howemouth\",\n" +
//                "      \"zipcode\": \"58804-1099\",\n" +
//                "      \"geo\": {\n" +
//                "        \"lat\": \"24.8918\",\n" +
//                "        \"lng\": \"21.8984\"\n" +
//                "      }\n" +
//                "    },\n" +
//                "    \"phone\": \"210.067.6132\",\n" +
//                "    \"website\": \"elvis.io\",\n" +
//                "    \"company\": {\n" +
//                "      \"name\": \"Johns Group\",\n" +
//                "      \"catchPhrase\": \"Configurable multimedia task-force\",\n" +
//                "      \"bs\": \"generate enterprise e-tailers\"\n" +
//                "    }\n" +
//                "  }");
//        System.out.println(b);

    }
}
