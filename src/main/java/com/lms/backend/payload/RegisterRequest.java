package com.lms.backend.payload;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
}
