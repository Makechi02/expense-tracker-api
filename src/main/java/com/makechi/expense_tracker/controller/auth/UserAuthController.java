package com.makechi.expense_tracker.controller.auth;

import com.makechi.expense_tracker.service.user.auth.UserAuthService;
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
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest request, final HttpServletRequest servletRequest) {
        UserAuthResponse response = userAuthService.register(request, servletRequest);
        return ResponseEntity.ok()
                .header("Authorization", response.getAccessToken())
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        UserAuthResponse response = userAuthService.login(request);
        return ResponseEntity.ok()
                .header("Authorization", response.getAccessToken())
                .body(response);
    }

}
