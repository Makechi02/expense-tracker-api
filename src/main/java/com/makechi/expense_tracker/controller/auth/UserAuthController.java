package com.makechi.expense_tracker.controller.auth;

import com.makechi.expense_tracker.service.user.auth.UserAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/auth")
@AllArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Registers a new user and returns an access token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<UserAuthResponse> register(
            @RequestBody @Parameter(description = "User registration details") UserRegisterRequest request,
            @Parameter(hidden = true) HttpServletRequest servletRequest) {
        UserAuthResponse response = userAuthService.register(request, servletRequest);
        return ResponseEntity.ok()
                .header("Authorization", response.getAccessToken())
                .body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Login a user", description = "Logs in a user and returns an access token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid username or password"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<UserAuthResponse> login(
            @RequestBody @Parameter(description = "User login credentials") UserLoginRequest request) {
        UserAuthResponse response = userAuthService.login(request);
        return ResponseEntity.ok()
                .header("Authorization", response.getAccessToken())
                .body(response);
    }
}
