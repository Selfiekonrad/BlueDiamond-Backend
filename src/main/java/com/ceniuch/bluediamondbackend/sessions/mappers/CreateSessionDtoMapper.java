package com.ceniuch.bluediamondbackend.sessions.mappers;

import com.ceniuch.bluediamondbackend.sessions.Session;
import com.ceniuch.bluediamondbackend.sessions.dtos.CreateSessionDto;
import com.ceniuch.bluediamondbackend.subjects.Subject;
import com.ceniuch.bluediamondbackend.users.User;

public class CreateSessionDtoMapper {

    public static Session fromCreateSessionDto(CreateSessionDto createSessionDto,
                                               User targetUser,
                                               Subject targetSubject) {
        return new Session(
            targetSubject,
            createSessionDto.duration(),
            createSessionDto.date(),
            targetUser
        );
    }

    private CreateSessionDtoMapper() {}
}
