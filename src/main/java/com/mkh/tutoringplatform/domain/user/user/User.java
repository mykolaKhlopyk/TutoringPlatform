package com.mkh.tutoringplatform.domain.user.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

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

    private Sex sex;

    private Set<Role> roles; //for authorization

    private Long userInitiatorId;

    private UserRole userRole; //for identification

    @Override
    public String toString() {
        return "User";
    }

    public enum UserRole {
        UNSPECIFIED,
        STUDENT,
        TEACHER
    }
}
