package com.ceniuch.bluediamondbackend.sessions;

import com.ceniuch.bluediamondbackend.subjects.Subject;
import com.ceniuch.bluediamondbackend.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;

// TODO: Make shared Sessions
@Entity(name = "session")
@Table(name = "session")
public class Session implements Comparable<Session> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String sessionId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "subject_id")
    Subject subject;

    @Column(name = "duration")
    private Duration duration;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_uid")
    User user;

    public Session(Subject subject, Duration duration, LocalDateTime startedAt, User user) {
        this.subject = subject;
        this.duration = duration;
        this.startedAt = startedAt;
        this.user = user;
    }

    public Session() {

    }

    public String getSessionId() {
        return sessionId;
    }

    public Subject getSubject() {
        return subject;
    }

    public User getUser() {
        return user;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime date) {
        this.startedAt = date;
    }

    @Override
    public int compareTo(Session o) {
        if (this.startedAt == null && o.getStartedAt() == null) return 0;
        if (this.startedAt == null) return -1;
        if (o.getStartedAt() == null) return 1;

        return this.startedAt.compareTo(o.getStartedAt());
    }
}