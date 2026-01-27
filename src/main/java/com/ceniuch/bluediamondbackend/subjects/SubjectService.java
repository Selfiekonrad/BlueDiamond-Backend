package com.ceniuch.bluediamondbackend.subjects;

import com.ceniuch.bluediamondbackend.subjects.dtos.CreateSubjectDto;
import com.ceniuch.bluediamondbackend.subjects.dtos.GetSubjectDto;
import com.ceniuch.bluediamondbackend.subjects.dtos.UpdateSubjectDto;
import com.ceniuch.bluediamondbackend.subjects.exceptions.SubjectExistsException;
import com.ceniuch.bluediamondbackend.subjects.exceptions.SubjectNotFoundException;
import com.ceniuch.bluediamondbackend.subjects.mappers.CreateSubjectDtoMapper;
import com.ceniuch.bluediamondbackend.subjects.mappers.GetSubjectDtoMapper;
import com.ceniuch.bluediamondbackend.users.User;
import com.ceniuch.bluediamondbackend.users.UserRepository;
import com.ceniuch.bluediamondbackend.users.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Transactional
    GetSubjectDto createSubjectDto(CreateSubjectDto createSubjectDto) {
        User targetUser = userRepository.findUserByUID(createSubjectDto.userUid()).orElseThrow(
                () -> new UserNotFoundException("User with UID " + createSubjectDto.userUid() + " not found.")
        );

        if (subjectRepository.existsSubjectByNameAndUser_UID(createSubjectDto.subjectName(), targetUser.getUID())) {
            throw new SubjectExistsException("Subject with name " + createSubjectDto.subjectName() +
                    " already exists for user with UID " + targetUser.getUID() + ".");
        }

        Subject newSubject = fromCreateSubjectDto(createSubjectDto, targetUser);
        Subject savedSubject = subjectRepository.save(newSubject);
        return toGetSubjectDto(savedSubject);
    }

    List<GetSubjectDto> getAllUserSubjects(String userUid) {
        User targetUser = userRepository.findUserByUID(userUid).orElseThrow(
                () -> new UserNotFoundException("User with UID " + userUid + " not found.")
        );
        return subjectRepository.findAllByUser(targetUser).stream().map(GetSubjectDtoMapper::toGetSubjectDto).toList();
    }

    void deleteSubject(String subjectId) {
        Subject targetSubject = subjectRepository.findSubjectBySubjectId(subjectId).orElseThrow(
                () -> new SubjectNotFoundException("Subject with ID " + subjectId + " not found.")
        );
        subjectRepository.delete(targetSubject);
    }

    @Transactional
    GetSubjectDto updateSubject(UpdateSubjectDto updateSubjectDto) {
        Subject targetSubject = subjectRepository.findSubjectBySubjectId(updateSubjectDto.subjectId()).orElseThrow(
                () -> new SubjectNotFoundException("Subject with ID " + updateSubjectDto.subjectId() + " not found.")
        );
        targetSubject.setName(updateSubjectDto.subjectName());
        Subject updatedSubject = subjectRepository.save(targetSubject);
        return toGetSubjectDto(updatedSubject);
    }
}
