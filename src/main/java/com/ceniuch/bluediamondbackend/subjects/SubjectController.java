package com.ceniuch.bluediamondbackend.subjects;

import com.ceniuch.bluediamondbackend.subjects.dtos.CreateSubjectDto;
import com.ceniuch.bluediamondbackend.subjects.dtos.GetSubjectDto;
import com.ceniuch.bluediamondbackend.subjects.dtos.UpdateSubjectDto;
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
@CrossOrigin(origins = "*")
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

    @Operation(
            summary = "Get all Subjects for a given user UID."
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<GetSubjectDto> getAllSubjects(@RequestParam String userUid) {
        return subjectService.getAllUserSubjects(userUid);
    }

    @Operation(
            summary = "Delete an existing subject." +
            "The subject to be deleted is chosen by the provided id. " +
            "If the subject doesn't exist, this operation will fail."
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteSubject(@PathVariable("id") String subjectId) {
        subjectService.deleteSubject(subjectId);
    }

    @Operation(
            summary = "Update an existing subject." +
            "The subject to be updated is chosen by the provided id. " +
            "If the subject doesn't exist, this operation will fail."
    )
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    GetSubjectDto updateSubject(@RequestBody UpdateSubjectDto updateSubjectDto) {
        return subjectService.updateSubject(updateSubjectDto);
    }
}
