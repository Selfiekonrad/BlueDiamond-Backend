package com.ceniuch.bluediamondbackend.subjects.dtos;

public record CreateSubjectDto(
    String subjectName,
    String userUid
) {
}
