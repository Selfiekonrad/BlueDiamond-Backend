package com.ceniuch.bluediamondbackend.users.mappers;

import com.ceniuch.bluediamondbackend.users.User;
import com.ceniuch.bluediamondbackend.users.dtos.GetUserDto;

public class GetUserDtoIudMapper {
    public static GetUserDto toGetUserDtoId(User user) {
        return new GetUserDto(
            user.getUID(),
            user.getUsername()
        );
    }

    private GetUserDtoIudMapper() {
    }
}
