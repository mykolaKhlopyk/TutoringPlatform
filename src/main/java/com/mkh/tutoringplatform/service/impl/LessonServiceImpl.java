package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.user.student.Group;
import com.mkh.tutoringplatform.domain.user.student.Lesson;
import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import com.mkh.tutoringplatform.repository.GroupRepository;
import com.mkh.tutoringplatform.repository.LessonRepository;
import com.mkh.tutoringplatform.repository.StudentRepository;
import com.mkh.tutoringplatform.repository.TeacherRepository;
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
    private final LessonRepository lessonRepository;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public void save(Lesson lesson, long group_id) {
        Group group = groupRepository.getOne(group_id);
        lesson.setGroup(group);
        lessonRepository.save(lesson);
    }

    @Override
    public List<Lesson> getLessonByTeacherId(long teacher_id, long student_id) {
        Teacher teacher = teacherRepository.getOne(teacher_id);
        Student student = studentRepository.getOne(student_id);
        return student.getGroups().stream().filter(group -> group.getTeacher().equals(teacher)).map(Group::getLessons).flatMap(List::stream).collect(Collectors.toList());
    }

    @Override
    public List<Lesson> getLessonInAboutOneDay(Student student) {
        student = studentRepository.getOne(student.getId());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        return student.getGroups().stream().map(Group::getLessons).flatMap(List::stream).filter(lesson -> lesson.getTimeStart().before(tomorrow)).collect(Collectors.toList());
    }
    @Override
    public List<Lesson> getLessonInAboutOneDay(Teacher teacher) {
        teacher = teacherRepository.getOne(teacher.getId());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        return teacher.getGroups().stream().map(Group::getLessons).flatMap(List::stream).filter(lesson -> lesson.getTimeStart().before(tomorrow)).collect(Collectors.toList());
    }
}
