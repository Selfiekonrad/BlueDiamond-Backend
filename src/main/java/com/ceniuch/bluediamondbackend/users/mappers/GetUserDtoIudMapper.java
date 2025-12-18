package com.ceniuch.bluediamondbackend.users.mappers;

import com.ceniuch.bluediamondbackend.users.User;
import com.ceniuch.bluediamondbackend.users.dtos.GetUserDtoUid;

public class GetUserDtoIudMapper {
    public static GetUserDtoUid toGetUserDtoId(User user) {
        return new GetUserDtoUid(
            user.getUID(),
            user.getUsername()
        );
    }

    private GetUserDtoIudMapper() {
    }
}
