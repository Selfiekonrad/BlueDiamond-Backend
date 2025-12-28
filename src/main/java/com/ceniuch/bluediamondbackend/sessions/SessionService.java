package com.ceniuch.bluediamondbackend.sessions;

import com.ceniuch.bluediamondbackend.sessions.dtos.CreateSessionDto;
import com.ceniuch.bluediamondbackend.sessions.dtos.GetSessionDto;
import com.ceniuch.bluediamondbackend.sessions.dtos.UpdateSessionDtoId;
import com.ceniuch.bluediamondbackend.sessions.exceptions.SessionNotFoundException;
import com.ceniuch.bluediamondbackend.sessions.mappers.GetSessionDtoMapper;
import com.ceniuch.bluediamondbackend.subjects.Subject;
import com.ceniuch.bluediamondbackend.subjects.SubjectRepository;
import com.ceniuch.bluediamondbackend.subjects.exceptions.SubjectNotFoundException;
import com.ceniuch.bluediamondbackend.users.User;
import com.ceniuch.bluediamondbackend.users.UserRepository;
import com.ceniuch.bluediamondbackend.users.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.ceniuch.bluediamondbackend.sessions.mappers.CreateSessionDtoMapper.fromCreateSessionDto;
import static com.ceniuch.bluediamondbackend.sessions.mappers.GetSessionDtoMapper.toGetSessionDto;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    public SessionService(SessionRepository sessionRepository, UserRepository userRepository, SubjectRepository subjectRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
    }

    @Transactional
    GetSessionDto createSession(CreateSessionDto createSessionDto) {
        User targetUser = userRepository.findUserByUID(createSessionDto.userUid()).orElseThrow(
                () -> new UserNotFoundException("User with UID " + createSessionDto.userUid() + " not found.")
        );
        Subject targetSubject = subjectRepository.findSubjectBySubjectId(createSessionDto.subjectId()).orElseThrow(
                () -> new SubjectNotFoundException("Subject with ID " + createSessionDto.subjectId() + " not found.")
        );
        Session newSession = fromCreateSessionDto(createSessionDto, targetUser, targetSubject);
        Session savedSession = sessionRepository.save(newSession);
        return toGetSessionDto(savedSession);
    }

    List<GetSessionDto> getAllSessions(String userUid) {
        List<Session> sessions = sessionRepository.findAllByUser_UID(userUid);
        return sessions.stream()
                .sorted(Comparator.reverseOrder())
                .map(GetSessionDtoMapper::toGetSessionDto)
                .toList();
    }

    @Transactional
    GetSessionDto updateSession(UpdateSessionDtoId updateSessionDtoId) {
        if ( updateSessionDtoId.sessionId() == null) {
            throw new IllegalArgumentException("Session ID must not be null.");
        }

        Session targetSession = sessionRepository.findBySessionId(updateSessionDtoId.sessionId()).orElseThrow(
                () -> new SessionNotFoundException("Session with ID " + updateSessionDtoId.sessionId() + " not found.")
        );

        targetSession.setCompleted(updateSessionDtoId.completed());
        return toGetSessionDto(sessionRepository.save(targetSession));
    }
}
