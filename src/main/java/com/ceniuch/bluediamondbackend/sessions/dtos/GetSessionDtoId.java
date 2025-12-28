package com.ceniuch.bluediamondbackend.sessions.dtos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record GetSessionDtoId(
    String sessionId,
    String subjectId,
    Duration duration,
    LocalDateTime date,
    LocalTime startTime,
    Boolean completed,
    String userId
) {
}
