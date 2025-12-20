package com.ceniuch.bluediamondbackend.subjects;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, String> {
    Optional<Subject> findSubjectBySubjectId(String subjectId);
}
