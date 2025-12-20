package com.ceniuch.bluediamondbackend.subjects.mappers;

import com.ceniuch.bluediamondbackend.subjects.Subject;
import com.ceniuch.bluediamondbackend.subjects.dtos.CreateSubjectDto;
import com.ceniuch.bluediamondbackend.users.User;

public class CreateSubjectDtoMapper {
    public static Subject fromCreateSubjectDto(CreateSubjectDto createSubjectDto, User targetUser) {
        return new Subject(
            createSubjectDto.subjectName(),
            targetUser
        );
    }

    private CreateSubjectDtoMapper() {}
}
