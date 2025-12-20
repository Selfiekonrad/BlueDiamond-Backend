package com.ceniuch.bluediamondbackend.subjects;

import com.ceniuch.bluediamondbackend.subjects.dtos.CreateSubjectDto;
import com.ceniuch.bluediamondbackend.subjects.dtos.GetSubjectDto;
import com.ceniuch.bluediamondbackend.users.User;
import com.ceniuch.bluediamondbackend.users.UserRepository;
import com.ceniuch.bluediamondbackend.users.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import static com.ceniuch.bluediamondbackend.subjects.mappers.CreateSubjectDtoMapper.fromCreateSubjectDto;
import static com.ceniuch.bluediamondbackend.subjects.mappers.GetSubjectDtoMapper.toGetSubjectDto;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public SubjectService(SubjectRepository subjectRepository, UserRepository userRepository) {
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    GetSubjectDto createSubjectDto(CreateSubjectDto createSubjectDto) {
        User targetUser = userRepository.findUserByUID(createSubjectDto.userUid()).orElseThrow(
                () -> new UserNotFoundException("User with UID " + createSubjectDto.userUid() + " not found.")
        );
        Subject newSubject = fromCreateSubjectDto(createSubjectDto, targetUser);
        Subject savedSubject = subjectRepository.save(newSubject);
        return toGetSubjectDto(savedSubject);
    }
}
