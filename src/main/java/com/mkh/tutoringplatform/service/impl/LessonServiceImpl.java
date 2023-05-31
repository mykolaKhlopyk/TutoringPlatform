package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.user.student.Group;
import com.mkh.tutoringplatform.domain.user.student.Lesson;
import com.mkh.tutoringplatform.repository.GroupRepository;
import com.mkh.tutoringplatform.repository.LessonRepository;
import com.mkh.tutoringplatform.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final GroupRepository groupRepository;

    @Override
    public void save(Lesson lesson, long group_id) {
        Group group = groupRepository.getOne(group_id);
        lesson.setGroup(group);
        lessonRepository.save(lesson);
    }
}
