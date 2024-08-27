package com.makechi.expense_tracker.controller;

import com.makechi.expense_tracker.dto.user.UserDto;
import com.makechi.expense_tracker.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userID}")
    public UserDto getUserById(@PathVariable int userID) {
        return userService.getUserById(userID);
    }

    @GetMapping("/email/{email}")
    public UserDto getUserById(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") int userId) {
        boolean deleted = userService.deleteUserById(userId);
        Map<Boolean, String> response = new HashMap<>();
        response.put(deleted, "User with id " + userId + " deleted successfully");
        return ResponseEntity.ok()
                .body(response);
    }

}
