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
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String sessionId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "subject_id")
    Subject subject;

    @Column(name = "duration")
    private Duration duration;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_uid")
    User user;

    public Session(Subject subject, Duration duration, LocalDateTime date, User user) {
        this.subject = subject;
        this.duration = duration;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}