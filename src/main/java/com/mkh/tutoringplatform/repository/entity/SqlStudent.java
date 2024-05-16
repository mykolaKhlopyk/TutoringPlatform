package com.mkh.tutoringplatform.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "students")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SqlStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private SqlUser user;

    @ManyToMany(mappedBy = "students")
    private List<SqlTeacher> teachers;

    @ManyToMany(mappedBy = "studentsWithRequest")
    private List<SqlCourse> requestedCourses;

//    @ManyToMany(mappedBy = "notConfirmedStudents")
//    private List<SqlTeacher> requestedTeachers;

    @ManyToMany(mappedBy = "students")
    private List<SqlGroup> groups;

    @ManyToMany(mappedBy = "students")
    private List<SqlCourse> courses;

    @Override
    public String toString() {
        return "Student";
    }
}
