package com.ceniuch.bluediamondbackend.sessions.dtos;

import com.ceniuch.bluediamondbackend.subjects.dtos.GetSubjectDto;
import com.ceniuch.bluediamondbackend.users.User;
import com.ceniuch.bluediamondbackend.users.dtos.GetUserDtoUid;

import java.time.Duration;
import java.time.LocalDateTime;

public record GetSessionDto(
    String sessionId,
    GetSubjectDto subject,
    Duration duration,
    LocalDateTime date,
    GetUserDtoUid user
) {
}
