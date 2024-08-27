package com.makechi.expense_tracker.service.user;

import com.makechi.expense_tracker.dto.user.UserDto;
import com.makechi.expense_tracker.dto.user.UserDtoMapper;
import com.makechi.expense_tracker.entity.User;
import com.makechi.expense_tracker.exception.ResourceNotFoundException;
import com.makechi.expense_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElse(null);
        if (user == null) throw new UsernameNotFoundException("user not found");
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userDtoMapper).toList();
    }

    @Override
    public UserDto getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        return userDtoMapper.apply(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found"));
        return userDtoMapper.apply(user);
    }

    @Override
    public boolean deleteUserById(int id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }

        userRepository.deleteById(id);
        log.info("User with id deleted: {}", id);
        return true;
    }

}
