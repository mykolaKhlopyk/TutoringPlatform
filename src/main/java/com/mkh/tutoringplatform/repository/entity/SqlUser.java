package com.mkh.tutoringplatform.repository.entity;

import com.mkh.tutoringplatform.domain.user.user.Role;
import com.mkh.tutoringplatform.domain.user.user.Sex;
import com.mkh.tutoringplatform.domain.user.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.validation.constraints.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SqlUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    @Email(message = "email is incorrect")
    @Column(name = "email")
    private String email;

    @NotNull
    @Size(min = 6, max = 16, message = "Username should be between 6 and 16 characters")
    @Pattern(regexp = "(?<![^\\W_])\\b[a-z]+(?:_[a-z]+)*\\b(?![^\\W_])", message = "username contains only small characters and _ between them")
    @Column(name = "username")
    private String username;

    @NotNull
    @NotEmpty(message = "not empty")
    @Size(min = 6, max = 255, message = "Password should be between 6 and 16 characters")
    @Column(name = "password")
    private String password;

    @NotNull
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Size(min = 2, max = 30, message = "Phone number should be between 2 and 30 characters")
    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private User.UserRole userRole;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToOne(mappedBy = "user")
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private SqlTeacher teacher;

    @OneToOne(mappedBy = "user")
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private SqlStudent student;

    @Override
    public String toString() {
        return "User";
    }
}
