package com.ceniuch.bluediamondbackend.subjects;

import com.ceniuch.bluediamondbackend.sessions.dtos.CreateSessionDto;
import com.ceniuch.bluediamondbackend.sessions.dtos.GetSessionDto;
import com.ceniuch.bluediamondbackend.subjects.dtos.CreateSubjectDto;
import com.ceniuch.bluediamondbackend.subjects.dtos.GetSubjectDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
    name = "Subject Controller",
    description = "Controller for CRUD operations on the subject"
)
@RestController
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Operation(
            summary = "Create a new Subject according to a provided CreateSubjectDto."
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    GetSubjectDto createSubject(@RequestBody CreateSubjectDto createSubjectDto) {
        return subjectService.createSubjectDto(createSubjectDto);
    }
}
