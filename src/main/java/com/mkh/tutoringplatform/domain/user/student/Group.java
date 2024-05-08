package com.mkh.tutoringplatform.domain.user.student;

import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min=6, max = 16, message = "Group should be between 6 and 16 characters")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "students_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @OneToMany(mappedBy = "group")
    private List<Lesson> lessons;

    @Override
    public String toString() {
        return "Group";
    }
}
