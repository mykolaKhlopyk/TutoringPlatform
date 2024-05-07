package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.exception.AccessDeniedException;
import com.mkh.tutoringplatform.domain.user.student.Group;
import com.mkh.tutoringplatform.domain.user.student.Lesson;
import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.repository.GroupRepository;
import com.mkh.tutoringplatform.repository.LessonRepository;
import com.mkh.tutoringplatform.repository.TeacherRepository;
import com.mkh.tutoringplatform.repository.UserRepository;
import com.mkh.tutoringplatform.service.GroupService;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    private final UserRepository userRepository;

    private final TeacherRepository teacherRepository;

    private final LessonRepository lessonRepository;

    @Override
    @Transactional
    public void createGroup(List<Long> users, String name, Teacher teacher) {
        List<Student> students = users.stream().map(userRepository::getOne).map(User::getStudent).collect(Collectors.toList());
        teacher = teacherRepository.getOne(teacher.getId());
        Group group = new Group();
        group.setName(name);
        group.setStudents(students);
        group.setTeacher(teacher);
        groupRepository.save(group);
    }

    @Override
    public List<Group> getGroups(Teacher authenticatedTeacher) {
        Teacher teacher = teacherRepository.getOne(authenticatedTeacher.getId());
        Hibernate.initialize(teacher.getGroups());
        return teacher.getGroups();
    }

    @Override
    @Transactional
    public List<Lesson> getLessons(long group_id, Teacher authenticatedTeacher) {
        Teacher teacher = teacherRepository.getOne(authenticatedTeacher.getId());
        if (teacher.getGroups().stream().map(Group::getId).noneMatch(id -> id == group_id)) {
            throw new AccessDeniedException();
        }
        Group group = groupRepository.getOne(group_id);
        Hibernate.initialize(group.getLessons());
        List<Lesson> finishedLessons = getFinishedLessons(group.getLessons());
        group.getLessons().removeAll(finishedLessons);
        groupRepository.save(group);
        finishedLessons.stream().forEach(lesson -> lessonRepository.delete(lesson));
        return group.getLessons();
    }

    @Override
    @Transactional
    public void deleteLesson(long lesson_id, long group_id) {
        Lesson lesson = lessonRepository.getOne(lesson_id);
        Group group = groupRepository.getOne(group_id);
        group.getLessons().remove(lesson);
        lessonRepository.delete(lesson);
        groupRepository.save(group);
    }

    @Override
    @Transactional
    public void deleteGroup(long group_id) {
        groupRepository.deleteById(group_id);
    }

    @Override
    public Group getGroup(long group_id) {
        return groupRepository.getOne(group_id);
    }

    private List<Lesson> getFinishedLessons(List<Lesson> lessons) {
        Date now = new Date();
        return lessons.stream().filter(lesson -> lesson.getTimeFinish().before(now)).collect(Collectors.toList());
    }
}
