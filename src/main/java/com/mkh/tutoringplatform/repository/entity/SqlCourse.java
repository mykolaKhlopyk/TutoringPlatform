package com.mkh.tutoringplatform.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedEntityGraph(name = "course-with-teacher",
        attributeNodes = @NamedAttributeNode("teacher"))
public class SqlCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 3, max = 16, message = "Course name should be between 3 and 16 characters")
    private String name;

    private String description;

    private String literature;

    private String tasks;

    private String links;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "students_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<SqlStudent> students;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "requested_students_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<SqlStudent> studentsWithRequest;

    @OneToMany(mappedBy = "course")
    private List<SqlGroup> groups;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private SqlTeacher teacher;

    @Override
    public String toString() {
        return "Course";
    }
}
