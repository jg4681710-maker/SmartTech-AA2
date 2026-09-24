package co.ucompensar.smarttech.controller;

import co.ucompensar.smarttech.dto.ExternalPostResponse;
import co.ucompensar.smarttech.service.ExternalApiService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/external")
public class ExternalApiController {

    private final ExternalApiService externalApiService;

    public ExternalApiController(
            ExternalApiService externalApiService) {

        this.externalApiService = externalApiService;
    }

    @GetMapping(
            value = "/posts",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<ExternalPostResponse> getPosts() {

        return externalApiService.findPosts();
    }
}