package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.user.Group;
import com.mkh.tutoringplatform.domain.user.Lesson;
import com.mkh.tutoringplatform.repository.GroupRepository;
import com.mkh.tutoringplatform.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public void createGroup(List<Long> studentsIds, String name, long courseId) {
        Group group = Group.builder()
                .name(name)
                .courseId(courseId)
                .studentsIds(studentsIds)
                .build();

        groupRepository.save(group);
    }

    @Override
    public List<Group> getGroupsFromCourse(long courseId) {
        return groupRepository.getGroupsFromCourse(courseId);
    }

    @Override
    public void deleteGroup(long groupId) {
        groupRepository.deleteById(groupId);
    }

    @Override
    public Group getGroupById(long groupId) {
        return groupRepository.findById(groupId).get();
    }

    @Override
    public List<Group> getTeacherGroups(long teacherId) {
        return groupRepository.getTeacherGroups(teacherId);
    }

    @Override
    public List<Group> getGroupsByIds(List<Long> groupsIds) {
        return groupRepository.getGroupsByIds(groupsIds);
    }

    private List<Lesson> getFinishedLessons(List<Lesson> lessons) {
        Date now = new Date();
        return lessons.stream().filter(lesson -> lesson.getTimeFinish().before(now)).collect(Collectors.toList());
    }
}
