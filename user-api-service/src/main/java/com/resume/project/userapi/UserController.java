package com.resume.project.userapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/comment")
public class UserController {

    // Injects the moderator's public URL from the Cloud Run environment variable
    @Value("${MODERATOR_SERVICE_URL}")
    private String moderatorServiceUrl;

    @PostMapping
    public Map<String, String> submitComment(@RequestBody UserCommentRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> requestBody = Map.of("text", request.getText());

        // This now uses the correct public cloud URL
        Map<String, String> moderationResponse = restTemplate.postForObject(
            moderatorServiceUrl,
            requestBody,
            Map.class
        );

        return moderationResponse;
    }
}