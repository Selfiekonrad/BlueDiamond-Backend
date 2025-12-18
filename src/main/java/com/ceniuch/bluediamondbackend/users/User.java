package com.ceniuch.bluediamondbackend.users;
import jakarta.persistence.*;

@Entity(name = "blueuser")
@Table(name = "blueuser")
public class User {
    @Id
    private String UID;

    @Column(name = "username")
    private String username;

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

    @Override
    public String toString() {
        return "User{" +
                "UID=" + UID +
                ", username='" + username + '\'' +
                '}';
    }
}
