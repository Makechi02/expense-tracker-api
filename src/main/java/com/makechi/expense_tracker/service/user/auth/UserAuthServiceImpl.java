package com.makechi.expense_tracker.service.user.auth;

import com.makechi.expense_tracker.controller.auth.UserAuthResponse;
import com.makechi.expense_tracker.controller.auth.UserLoginRequest;
import com.makechi.expense_tracker.controller.auth.UserRegisterRequest;
import com.makechi.expense_tracker.dto.user.UserDtoMapper;
import com.makechi.expense_tracker.entity.User;
import com.makechi.expense_tracker.exception.DuplicateResourceException;
import com.makechi.expense_tracker.repository.UserRepository;
import com.makechi.expense_tracker.service.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserAuthServiceImpl implements UserAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDtoMapper userDtoMapper;

    @Override
    public UserAuthResponse register(UserRegisterRequest request, final HttpServletRequest servletRequest) {
        boolean existsByEmail = userRepository.existsByEmail(request.email());
        if (existsByEmail) throw new DuplicateResourceException("email already taken");

        var user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build();

        user = userRepository.save(user);
        log.info("User saved: {}", user);

        var accessToken = jwtService.generateToken(user);

        return UserAuthResponse.builder()
                .accessToken(accessToken)
                .message("Registration Success!")
                .user(userDtoMapper.apply(user))
                .build();
    }

    @Override
    public UserAuthResponse login(UserLoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        var accessToken = jwtService.generateToken(user);

        return UserAuthResponse.builder()
                .accessToken(accessToken)
                .message("Login success")
                .user(userDtoMapper.apply(user))
                .build();
    }
}
