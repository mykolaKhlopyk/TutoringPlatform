package com.mkh.tutoringplatform.domain.user.student;

import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import com.mkh.tutoringplatform.domain.user.user.User;
import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany(mappedBy = "students")
    private List<Teacher> teachers;

    @ManyToMany(mappedBy = "notConfirmedStudents")
    private List<Teacher> requestedTeachers;

    @ManyToMany(mappedBy = "students")
    private List<Group> groups;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses;

    @Override
    public String toString() {
        return "Student";
    }
}
