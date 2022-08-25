package fr.m2i.javaapirest.tpuser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.persistence.*;

@Entity
@Table(name = "utilisateurs")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lastname", length = 100, nullable = false)
    private String lastname;

    @Column(name = "firstname", length = 100, nullable = false)
    private String firstname;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "ENUM('normal','admin') NOT NULL")
    private Role role;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "password", length = 40, nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    public User() {
        
    }

    public User(String lastname, String firstname, Role role, String email, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void copy(User data) {

        if (data == null) {
            return;
        }

        if (data.getLastname() != null) {
            this.lastname = data.getLastname();
        }

        if (data.getFirstname() != null) {
            this.firstname = data.getFirstname();
        }

        if (data.getEmail() != null) {
            this.email = data.getEmail();
        }

        if (data.getPassword() != null) {
            this.password = data.getPassword();
        }

        if (data.getRole() != null) {
            this.role = data.getRole();
        }
    }

    public boolean hasAFieldEmpty() {
        return getLastname() == null ||
                getFirstname() == null ||
                getEmail() == null ||
                getPassword() == null ||
                getRole() == null;
    }
}
