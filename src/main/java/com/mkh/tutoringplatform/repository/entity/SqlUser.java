package com.mkh.tutoringplatform.repository.entity;

import com.mkh.tutoringplatform.domain.user.user.Role;
import com.mkh.tutoringplatform.domain.user.user.Sex;
import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.repository.entity.SqlStudent;
import com.mkh.tutoringplatform.repository.entity.SqlTeacher;
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
    private long id;

    @NotNull
    @Email(message = "email is incorrect")
    private String email;

    @NotNull
    @Size(min = 6, max = 16, message = "Username should be between 6 and 16 characters")
    @Pattern(regexp = "(?<![^\\W_])\\b[a-z]+(?:_[a-z]+)*\\b(?![^\\W_])", message = "username contains only small characters and _ between them")
    private String username;

    @NotNull
    @NotEmpty(message = "not empty")
    @Size(min = 6, max = 255, message = "Password should be between 6 and 16 characters")
    private String password;

    @NotNull
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotNull
    @Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
    private String surname;

    @NotNull
    @Size(min = 2, max = 30, message = "Phone number should be between 2 and 30 characters")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private User.UserRole userRole;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToOne(mappedBy = "user")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private SqlTeacher teacher;

    @OneToOne(mappedBy = "user")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private SqlStudent student;

    @Override
    public String toString() {
        return "User";
    }
}
