package com.ceniuch.bluediamondbackend.sessions.mappers;

import com.ceniuch.bluediamondbackend.sessions.Session;
import com.ceniuch.bluediamondbackend.sessions.dtos.GetSessionDto;

import static com.ceniuch.bluediamondbackend.subjects.mappers.GetSubjectDtoMapper.toGetSubjectDto;
import static com.ceniuch.bluediamondbackend.users.mappers.GetUserDtoIudMapper.toGetUserDtoId;

public class GetSessionDtoMapper {
    public static GetSessionDto toGetSessionDto(Session session) {
        return new GetSessionDto(
            session.getSessionId(),
            toGetSubjectDto(session.getSubject()),
            session.getDuration(),
            session.getDate(),
            session.isCompleted(),
            toGetUserDtoId(session.getUser())
        );
    }

    private GetSessionDtoMapper() {}
}
