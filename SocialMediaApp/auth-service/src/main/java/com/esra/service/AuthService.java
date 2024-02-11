package com.esra.service;

import com.esra.dto.request.ActivationRequestDto;
import com.esra.dto.request.LoginRequestDto;
import com.esra.dto.request.RegisterRequestDto;
import com.esra.dto.response.RegisterResponseDto;
import com.esra.entity.Auth;
import com.esra.exception.AuthServiceException;
import com.esra.exception.ErrorType;
import com.esra.mapper.AuthMapper;
import com.esra.repository.AuthRepository;
import com.esra.utility.CodeGenerator;
import com.esra.utility.ServiceManager;
import com.esra.utility.enums.EStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        super(authRepository);
        this.authRepository = authRepository;
    }

    public RegisterResponseDto register1(RegisterRequestDto dto) {
        Auth auth = AuthMapper.INSTANCE.fromRegisterRequestDtoToAuth(dto);
        if(!authRepository.findOptionalByEmailContainingIgnoreCase(dto.getEmail()).isEmpty()){
            throw new AuthServiceException(ErrorType.ERROR_DUPLICATE_EMAIL);
        }
        if(auth.getPassword().isBlank()){
            throw new RuntimeException("Sifre bos birakılamaz");
        }
        authRepository.save(auth);
        return AuthMapper.INSTANCE.fromAuthToRegisterResponseDto(auth);
    }

    public RegisterResponseDto register(RegisterRequestDto dto) {
        Auth auth = AuthMapper.INSTANCE.fromRegisterRequestDtoToAuth(dto);
        auth.setActivationCode(CodeGenerator.generateCode());
        save(auth);
        return AuthMapper.INSTANCE.fromAuthToRegisterResponseDto(auth);
    }

    public Boolean login(LoginRequestDto dto) {
        Optional<Auth> authOptional = authRepository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (authOptional.isEmpty())
            throw new AuthServiceException(ErrorType.ERROR_INVALID_LOGIN_PARAMETER);
        return true;
    }

    public Boolean activation(ActivationRequestDto dto) {
        Optional<Auth> authOptional = authRepository.findOptionalByEmail(dto.getEmail());
        if(!authOptional.get().getActivationCode().equals(dto.getActivationCode()))
            throw new AuthServiceException(ErrorType.ERROR_VERIFICATION_CODE);
        authOptional.get().setStatus(EStatus.ACTIVE);
        save(authOptional.get());
        return true;
    }
}
