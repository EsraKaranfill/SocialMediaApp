package com.esra.controller;

import com.esra.dto.request.ActivationRequestDto;
import com.esra.dto.request.LoginRequestDto;
import com.esra.dto.request.RegisterRequestDto;
import com.esra.dto.response.RegisterResponseDto;
import com.esra.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.esra.constant.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {
    private final AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<Boolean> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/activation-code-verification")
    public ResponseEntity<Boolean> ActivationCodeVerification(@RequestBody ActivationRequestDto dto){
        return ResponseEntity.ok(authService.activation(dto));
    }

}