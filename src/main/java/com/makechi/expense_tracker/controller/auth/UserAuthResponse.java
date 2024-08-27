package com.makechi.expense_tracker.controller.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.makechi.expense_tracker.dto.user.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAuthResponse {
    @JsonProperty("access_token")
    private String accessToken;

    private String message;

    private UserDto user;
}
