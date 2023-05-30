package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.user.student.Lesson;
import com.mkh.tutoringplatform.repository.LessonRepository;
import com.mkh.tutoringplatform.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;

    @Override
    public void save(Lesson lesson) {
        lessonRepository.save(lesson);
    }
}
