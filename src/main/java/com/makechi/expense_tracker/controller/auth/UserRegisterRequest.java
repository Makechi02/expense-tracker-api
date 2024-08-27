package com.makechi.expense_tracker.controller.auth;

import com.makechi.expense_tracker.entity.Role;

public record UserRegisterRequest(
        String name,
        String email,
        String password,
        Role role
) {}
