package com.ceniuch.bluediamondbackend.sessions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {
    List<Session> findAllByUser_UID(String uid);

    Optional<Session> findBySessionId(String sessionId);
}
