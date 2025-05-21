package com.projects.shopIt.controllers;

import com.projects.shopIt.dtos.JwtResponse;
import com.projects.shopIt.dtos.LoginRequest;
import com.projects.shopIt.dtos.UserDto;
import com.projects.shopIt.mappers.UserMapper;
import com.projects.shopIt.repositories.UserRepository;
import com.projects.shopIt.services.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        var token = jwtService.generateToken(loginRequest.getEmail());


        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me() {
       var authentication=SecurityContextHolder.getContext().getAuthentication();
       var email= (String)authentication.getPrincipal();
       var user = userRepository.findByEmail(email).orElse(null);
       if (user == null) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
       var userDto = userMapper.toDto(user);
       return ResponseEntity.ok(userDto);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<Void> handleBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
