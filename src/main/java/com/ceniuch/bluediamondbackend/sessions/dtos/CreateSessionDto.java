package com.ceniuch.bluediamondbackend.sessions.dtos;

import java.time.Duration;
import java.time.LocalTime;

public record CreateSessionDto(
    String userUid,
    String subjectId,
    LocalTime startTime,
    Duration duration
) {
}
