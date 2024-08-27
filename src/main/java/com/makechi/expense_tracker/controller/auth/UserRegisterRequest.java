package com.makechi.expense_tracker.controller.auth;

import com.makechi.expense_tracker.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request payload for user registration")
public record UserRegisterRequest(
        @Schema(description = "Username of the user", example = "John Doe")
        String name,
        @Schema(description = "Email of the user", example = "test@email.com")
        String email,
        @Schema(description = "Password of the user", example = "password123")
        String password,
        @Schema(description = "Role of the user", example = "USER")
        Role role
) {}
