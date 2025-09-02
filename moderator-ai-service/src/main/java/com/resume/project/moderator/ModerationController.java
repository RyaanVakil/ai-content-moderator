package com.resume.project.moderator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/moderate")
public class ModerationController {

    private final ModerationService moderationService;

    // Spring will automatically provide the ModerationService instance
    public ModerationController(ModerationService moderationService) {
        this.moderationService = moderationService;
    }

    @PostMapping
    public Map<String, String> moderate(@RequestBody ModerationRequest request) {
        String decision = moderationService.moderateText(request.getText());
        // We return a simple JSON object like {"decision": "APPROVE"}
        return Map.of("decision", decision);
    }
}