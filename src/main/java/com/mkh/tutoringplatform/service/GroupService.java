package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.domain.user.Group;

import java.util.List;

public interface GroupService {

    void createGroup(List<Long> studentsIds, String name, long courseId);

    List<Group> getGroupsFromCourse(long courseId);

    void deleteGroup(long groupId);

    Group getGroupById(long groupId);

    List<Group> getTeacherGroups(long teacherId);

    List<Group> getGroupsByIds(List<Long> groupsIds);
}
