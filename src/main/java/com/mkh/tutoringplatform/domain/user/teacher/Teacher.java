package com.mkh.tutoringplatform.domain.user.teacher;

import com.mkh.tutoringplatform.domain.user.student.Grade;
import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.domain.user.user.Role;
import com.mkh.tutoringplatform.domain.user.user.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Min(value = 0)
    private int experience;

    @Column(name = "sum_of_votes")
    private int sumOfVotes;

    @Column(name = "number_of_votes")
    private int numberOfVotes;

    @Column(name = "pay_per_hour")
    @Min(value = 0)
    private int payPerHour;

    @NotNull
    @Column(name = "registered_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date registeredAt;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Place place;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "teachers_grades", joinColumns = @JoinColumn(name = "teacher_id"))
    @Column(name = "grade_id")
    private Set<Grade> grades;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "teachers_subjects", joinColumns = @JoinColumn(name = "teacher_id"))
    @Column(name = "subject_id")
    private Set<Subject> subjects;

    @ManyToMany
    @JoinTable(
            name = "teachers_students",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    @ManyToMany
    @JoinTable(
            name = "not_confirmed_teachers_students",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> notConfirmedStudents;
}
