package com.ceniuch.bluediamondbackend.sessions.dtos;

import java.time.Duration;
import java.time.LocalDateTime;

public record CreateSessionDto(
    String userUid,
    String subjectId,
    Duration duration,
    LocalDateTime date
) {
}
