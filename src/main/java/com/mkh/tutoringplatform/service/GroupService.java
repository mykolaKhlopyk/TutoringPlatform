package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.domain.user.student.Group;
import com.mkh.tutoringplatform.domain.user.teacher.Teacher;

import java.util.List;

public interface GroupService {
    void createGroup(List<Long> users, String name, Teacher teacher);
    List<Group> getGroups(Teacher authenticatedTeacher);
}
