package com.ceniuch.bluediamondbackend.users.mappers;

import com.ceniuch.bluediamondbackend.users.User;
import com.ceniuch.bluediamondbackend.users.dtos.CreateUserDto;

public class CreateUserDtoMapper {
    public static User fromCreateUserDto(CreateUserDto createUserDto) {
        return new User(createUserDto.username());
    }

    private CreateUserDtoMapper() {
    }
}
