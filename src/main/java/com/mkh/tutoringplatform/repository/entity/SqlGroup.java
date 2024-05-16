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
@Table(name = "groups")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SqlGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min=6, max = 16, message = "Group should be between 6 and 16 characters")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "students_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<SqlStudent> students;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private SqlCourse course;

    @OneToMany(mappedBy = "group")
    private List<SqlLesson> lessons;

    @Override
    public String toString() {
        return "Group";
    }
}
