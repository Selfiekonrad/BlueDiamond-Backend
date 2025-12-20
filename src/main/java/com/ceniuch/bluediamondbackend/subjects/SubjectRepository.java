package com.ceniuch.bluediamondbackend.subjects;

import com.ceniuch.bluediamondbackend.subjects.dtos.GetSubjectDto;
import com.ceniuch.bluediamondbackend.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, String> {
    Optional<Subject> findSubjectBySubjectId(String subjectId);

    List<Subject> findAllByUser_UID(String userUID);

    List<Subject> findAllByUser(User user);

    boolean existsSubjectByNameAndUser_UID(String name, String userUID);
}
