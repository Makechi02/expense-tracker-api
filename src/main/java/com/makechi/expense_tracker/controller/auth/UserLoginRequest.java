package com.makechi.expense_tracker.controller.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request payload for user login")
public record UserLoginRequest(
        @Schema(description = "Email of the user", example = "test@example.com")
        String email,
        @Schema(description = "Password of the user", example = "password123")
        String password
) {
}
