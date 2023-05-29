package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.user.student.Group;
import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.repository.GroupRepository;
import com.mkh.tutoringplatform.repository.TeacherRepository;
import com.mkh.tutoringplatform.repository.UserRepository;
import com.mkh.tutoringplatform.service.GroupService;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    @Override
    @Transactional
    public void createGroup(List<Long> users, String name, Teacher teacher) {
        List<Student> students = users.stream().map(userRepository::getOne).map(User::getStudent).collect(Collectors.toList());
        teacher=teacherRepository.getOne(teacher.getId());
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
}
