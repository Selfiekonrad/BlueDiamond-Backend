package com.ceniuch.bluediamondbackend.sessions.dtos;

import java.time.Duration;
import java.time.LocalDateTime;

public record UpdateSessionDtoId(
    String sessionId,
    String subjectId,
    Duration duration,
    LocalDateTime startedAt,
    String userId
) {
}
