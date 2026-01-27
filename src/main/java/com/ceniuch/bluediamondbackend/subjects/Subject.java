package com.ceniuch.bluediamondbackend.subjects;

import com.ceniuch.bluediamondbackend.sessions.Session;
import com.ceniuch.bluediamondbackend.users.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "subject")
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String subjectId;

    @Column(name = "name", nullable = false)
    String name;

    @ManyToOne
    @JoinColumn(name = "user_uid")
    private User user;

    // TODO: orphanRemoval and CascadeType
    @OneToMany(
            mappedBy = "subject",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Session> sessions = new ArrayList<>();

    public Subject(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Subject() {
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setName(String name) {
        this.name = name;
    }
}

