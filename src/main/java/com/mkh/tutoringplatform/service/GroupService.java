package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.domain.user.Group;
import com.mkh.tutoringplatform.domain.user.Lesson;
import com.mkh.tutoringplatform.domain.user.Teacher;

import java.util.List;

public interface GroupService {

    void createGroup(List<Long> users, String name, Teacher teacher);

    List<Group> getGroups(Teacher authenticatedTeacher);

    List<Lesson> getLessons(long group_id, Teacher authenticatedTeacher);

    void deleteLesson(long lesson_id, long group_id);

    void deleteGroup(long group_id);

    Group getGroup(long group_id);
}
