package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.user.Lesson;
import com.mkh.tutoringplatform.domain.user.Student;
import com.mkh.tutoringplatform.domain.user.Teacher;
import com.mkh.tutoringplatform.repository.LessonRepository;
import com.mkh.tutoringplatform.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Override
    public void createLesson(long groupId, String lessonName, Date timeStart, int duration) {
        Lesson lesson = Lesson.builder()
                .name(lessonName)
                .duration(duration)
                .timeStart(timeStart)
                .groupId(groupId)
                .build();
        lessonRepository.save(lesson);
    }

    @Override
    public List<Lesson> getTeacherLessons(long teacherId) {
        return lessonRepository.getTeacherLessons(teacherId);
    }

    @Override
    public List<Lesson> getTodayLessonForStudent(Student student) {
        return getAllLessonsFromGroups(student.getGroupsIds(), getPredicateForTodayLessons());
    }

    @Override
    public List<Lesson> getTodayLessonForTeacher(Teacher teacher) {
        return getAllLessonsFromGroups(teacher.getGroupsIds(), getPredicateForTodayLessons());
    }

    @Override
    public List<Lesson> getLessonsFromGroup(long groupId) {
        return lessonRepository.getLessonsFromGroups(List.of(groupId));
    }

    @Override
    public void deleteLesson(long lessonId) {
        lessonRepository.deleteById(lessonId);
    }

    private Predicate<Lesson> getPredicateForTodayLessons() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        return lesson -> lesson.getTimeStart().before(tomorrow);
    }

    private List<Lesson> getAllLessonsFromGroups(List<Long> groupIds, Predicate<Lesson> predicateForLessons) {
        var lessons = lessonRepository.getLessonsFromGroups(groupIds);
        return lessons.stream().filter(predicateForLessons).toList();
    }
}
