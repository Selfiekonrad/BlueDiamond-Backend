package com.ceniuch.bluediamondbackend.subjects.mappers;

import com.ceniuch.bluediamondbackend.subjects.Subject;
import com.ceniuch.bluediamondbackend.subjects.dtos.GetSubjectDto;

public class GetSubjectDtoMapper {
    public static GetSubjectDto toGetSubjectDto(Subject subject) {
        return new GetSubjectDto(
                subject.getName()
        );
    }

    private GetSubjectDtoMapper() {}
}
