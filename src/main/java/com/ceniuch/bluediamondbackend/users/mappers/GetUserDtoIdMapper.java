package com.ceniuch.bluediamondbackend.users.mappers;

import com.ceniuch.bluediamondbackend.users.User;
import com.ceniuch.bluediamondbackend.users.dtos.GetUserDtoId;

public class GetUserDtoIdMapper {
    public static GetUserDtoId toGetUserDtoId(User user) {
        return new GetUserDtoId(
            user.getId(),
            user.getUsername()
        );
    }

    private GetUserDtoIdMapper() {
    }
}
