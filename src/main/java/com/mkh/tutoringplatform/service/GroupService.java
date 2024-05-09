package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.domain.user.Group;
import com.mkh.tutoringplatform.domain.user.Teacher;

import java.util.List;

public interface GroupService {

    void createGroup(List<Long> studentsIds, String name, Teacher teacher);

    List<Group> getGroups(Teacher authenticatedTeacher);

    void deleteGroup(long groupId);

    Group findById(long groupId);
}
