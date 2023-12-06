package com.mkh.tutoringplatform.domain.user.teacher;

import com.mkh.tutoringplatform.domain.user.student.Course;
import com.mkh.tutoringplatform.domain.user.student.Group;
import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.domain.user.user.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.Min;
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

    @NotNull
    @Column(name = "registered_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date registeredAt;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "teachers_subjects", joinColumns = @JoinColumn(name = "teacher_id"))
//    @Column(name = "subject_id")
//    private Set<Subject> subjects;

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

    @OneToMany(mappedBy = "teacher")
    private List<Group> groups;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;
}
