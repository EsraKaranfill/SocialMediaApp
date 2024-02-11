package com.esra.mapper;

import com.esra.dto.request.RegisterRequestDto;
import com.esra.dto.response.RegisterResponseDto;
import com.esra.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {

    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    Auth fromRegisterRequestDtoToAuth(final RegisterRequestDto dto);
    RegisterResponseDto fromAuthToRegisterResponseDto(final Auth auth);
}