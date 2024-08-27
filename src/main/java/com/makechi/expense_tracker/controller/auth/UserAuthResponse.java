package com.makechi.expense_tracker.controller.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.makechi.expense_tracker.dto.user.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Response payload for Authentication")
public class UserAuthResponse {
    @JsonProperty("access_token")
    @Schema(description = "Access token for the user", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String accessToken;

    @Schema(description = "Response message after authentication", example = "Login success")
    private String message;

    @Schema(description = "Authenticated User Details")
    private UserDto user;
}
