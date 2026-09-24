package co.ucompensar.smarttech.service;

import co.ucompensar.smarttech.dto.ExternalPostResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ExternalApiService {

    private final RestClient restClient;

    public ExternalApiService(RestClient.Builder restClientBuilder) {

        this.restClient = restClientBuilder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    public List<ExternalPostResponse> findPosts() {

        return restClient
                .get()
                .uri("/posts")
                .retrieve()
                .body(
                        new ParameterizedTypeReference<
                                List<ExternalPostResponse>
                                >() {
                        }
                );
    }
}