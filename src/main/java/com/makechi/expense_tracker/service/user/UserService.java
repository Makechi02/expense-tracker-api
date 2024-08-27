package com.makechi.expense_tracker.service.user;

import com.makechi.expense_tracker.dto.user.UserDto;
import com.makechi.expense_tracker.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDto> getAllUsers();

    UserDto getUserById(int id);

    UserDto getUserByEmail(String email);

    boolean deleteUserById(int id);

    User findByUsername(String username);
}
