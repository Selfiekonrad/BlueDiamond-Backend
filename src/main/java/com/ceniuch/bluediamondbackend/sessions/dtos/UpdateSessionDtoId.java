package com.ceniuch.bluediamondbackend.sessions.dtos;

public record UpdateSessionDtoId(
    String sessionId,
    Boolean completed
) {
}