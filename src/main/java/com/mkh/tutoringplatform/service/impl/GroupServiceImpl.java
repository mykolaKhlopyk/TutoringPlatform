package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.exception.AccessDeniedException;
import com.mkh.tutoringplatform.domain.user.Group;
import com.mkh.tutoringplatform.domain.user.Lesson;
import com.mkh.tutoringplatform.domain.user.Teacher;
import com.mkh.tutoringplatform.repository.GroupRepository;
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
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public void createGroup(List<Long> studentsIds, String name, Teacher teacher) {
        Group group = Group.builder()
                .name(name)
                .teacherId(teacher.getId())
                .studentsIds(studentsIds)
                .build();

        groupRepository.save(group);
    }

    @Override
    public List<Group> getGroups(Teacher authenticatedTeacher) {
        return groupRepository.getGroups(authenticatedTeacher.getGroupsIds());
    }

    @Override
    public void deleteGroup(long groupId) {
        groupRepository.deleteById(groupId);
    }

    @Override
    public Group findById(long groupId) {
        return groupRepository.findById(groupId).get();
    }

    private List<Lesson> getFinishedLessons(List<Lesson> lessons) {
        Date now = new Date();
        return lessons.stream().filter(lesson -> lesson.getTimeFinish().before(now)).collect(Collectors.toList());
    }
}
