package com.mkh.tutoringplatform.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "teachers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SqlTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private SqlUser user;

    @NotNull
    @Column(name = "registered_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date registeredAt;

    @ManyToMany
    @JoinTable(
            name = "teachers_students",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<SqlStudent> students;

//    @ManyToMany
//    @JoinTable(
//            name = "not_confirmed_teachers_students",
//            joinColumns = @JoinColumn(name = "teacher_id"),
//            inverseJoinColumns = @JoinColumn(name = "student_id"))
//    private List<SqlStudent> notConfirmedStudents;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SqlCourse> courses;

    @Override
    public String toString() {
        return "Teacher";
    }
}
