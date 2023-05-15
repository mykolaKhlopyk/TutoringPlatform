package com.mkh.tutoringplatform.domain.user.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Length(min = 6, max = 16, message = "Username should be between 6 and 16 characters")
    @Pattern(regexp = "(?<![^\\W_])\\b[a-z]+(?:_[a-z]+)*\\b(?![^\\W_])")
    private String username;

    @NotNull
    @Length(min = 6, max = 16, message = "Password should be between 6 and 16 characters")
    private String password;

    @NotNull
    @Length(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotNull
    @Length(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
    private String surname;

    @NotNull
    @Length(min = 2, max = 30, message = "Phone number should be between 2 and 30 characters")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}
