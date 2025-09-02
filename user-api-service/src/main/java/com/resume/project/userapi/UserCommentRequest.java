package com.resume.project.userapi;

import lombok.Data;

@Data
public class UserCommentRequest {
    private String username;
    private String text;
}