package com.makechi.expense_tracker.service.user.auth;

import com.makechi.expense_tracker.controller.auth.UserAuthResponse;
import com.makechi.expense_tracker.controller.auth.UserLoginRequest;
import com.makechi.expense_tracker.controller.auth.UserRegisterRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface UserAuthService {
    UserAuthResponse register(UserRegisterRequest request, HttpServletRequest servletRequest);

    UserAuthResponse login(UserLoginRequest request);
}
