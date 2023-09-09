package dev.danvega.restclient.post;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PostService {

    private final RestClient restClient;
    private static final String URI_ID = "/posts/{id}";
    private static final String URI = "/posts";
    private static final String BASE = "https://jsonplaceholder.typicode.com";

    public PostService() {
        restClient = RestClient.builder()
                .baseUrl(BASE)
                .build();
    }

    List<Post> findAll() {
        return restClient.get()
                .uri(URI)
                .retrieve()
                .body(new ParameterizedTypeReference<List<Post>>() {
                });
    }

    Post findById(int id) {
        return restClient.get()
                .uri(URI_ID, id)
                .retrieve()
                .body(Post.class);
    }

    Post create(Post post) {
        return restClient.post()
                .uri(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .body(post)
                .retrieve()
                .body(Post.class);
    }

    Post update(Integer id, Post post) {
        return restClient.put()
                .uri(URI_ID, id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(post)
                .retrieve()
                .body(Post.class);
    }

    void delete(Integer id) {
        restClient.delete()
                .uri(URI_ID, id)
                .retrieve()
                .toBodilessEntity();
    }

}
