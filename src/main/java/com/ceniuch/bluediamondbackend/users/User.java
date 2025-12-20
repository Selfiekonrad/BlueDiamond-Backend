package com.ceniuch.bluediamondbackend.users;
import com.ceniuch.bluediamondbackend.sessions.Session;
import com.ceniuch.bluediamondbackend.subjects.Subject;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "blueuser")
@Table(name = "blueuser")
public class User {
    @Id
    private String UID;

    @Column(name = "username")
    private String username;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.REMOVE,
            // TODO: orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Subject> subjects = new ArrayList<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.REMOVE
    )
    private List<Session> sessions = new ArrayList<>();

    public User() {
    }

    public User(String UID, String username) {
        this.UID = UID;
        this.username = username;
    }

    public String getUID() {
        return UID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "User{" +
                "UID=" + UID +
                ", username='" + username + '\'' +
                '}';
    }
}
