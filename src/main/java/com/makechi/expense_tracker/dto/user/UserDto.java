package com.makechi.expense_tracker.dto.user;

import com.makechi.expense_tracker.entity.Role;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {
    private int id;
    private String name;
    private String email;
    private Role role;
}
