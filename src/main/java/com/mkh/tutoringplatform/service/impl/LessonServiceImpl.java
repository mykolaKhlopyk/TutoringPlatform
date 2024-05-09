package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.user.Group;
import com.mkh.tutoringplatform.domain.user.Lesson;
import com.mkh.tutoringplatform.domain.user.Student;
import com.mkh.tutoringplatform.domain.user.Teacher;
import com.mkh.tutoringplatform.repository.jpa.JpaGroupRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaLessonRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaStudentRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaTeacherRepository;
import com.mkh.tutoringplatform.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class LessonServiceImpl implements LessonService {
    private final JpaLessonRepository jpaLessonRepository;
    private final JpaGroupRepository jpaGroupRepository;
    private final JpaTeacherRepository jpaTeacherRepository;
    private final JpaStudentRepository jpaStudentRepository;

    @Override
    @Transactional
    public void save(Lesson lesson, long group_id) {
        Group group = jpaGroupRepository.getOne(group_id);
        lesson.setGroup(group);
        jpaLessonRepository.save(lesson);
    }

    @Override
    public List<Lesson> getLessonByTeacherId(long teacher_id, long student_id) {
        Teacher teacher = jpaTeacherRepository.getOne(teacher_id);
        Student student = jpaStudentRepository.getOne(student_id);
        return student.getGroups().stream().filter(group -> group.getTeacher().equals(teacher)).map(Group::getLessons).flatMap(List::stream).collect(Collectors.toList());
    }

    @Override
    public List<Lesson> getLessonInAboutOneDay(Student student) {
        student = jpaStudentRepository.getOne(student.getId());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        return student.getGroups().stream().map(Group::getLessons).flatMap(List::stream).filter(lesson -> lesson.getTimeStart().before(tomorrow)).collect(Collectors.toList());
    }
    @Override
    public List<Lesson> getLessonInAboutOneDay(Teacher teacher) {
        teacher = jpaTeacherRepository.getOne(teacher.getId());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        return teacher.getGroups().stream().map(Group::getLessons).flatMap(List::stream).filter(lesson -> lesson.getTimeStart().before(tomorrow)).collect(Collectors.toList());
    }
}
