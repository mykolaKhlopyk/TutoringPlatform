package com.mkh.tutoringplatform.web.controller;

import com.mkh.tutoringplatform.domain.user.Lesson;
import com.mkh.tutoringplatform.service.GroupService;
import com.mkh.tutoringplatform.service.LessonService;
import com.mkh.tutoringplatform.web.response.LessonInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.mkh.tutoringplatform.web.utils.ControllerUtils.getAuthenticatedUser;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    private final GroupService groupService;

    @GetMapping("/teacher")
    public String getAllTeacherLessons(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        long teacherId = getAuthenticatedUser(userDetails).getUserInitiatorId();

        var lessons = lessonService.getTeacherLessons(teacherId);
        var groupsIds = lessons.stream().map(Lesson::getGroupId).distinct().toList();
        var groups = groupService.getGroupsByIds(groupsIds);

        var lessonsInfos = lessons.stream().map(
                lesson -> {
                    var group = groups.stream().filter(it -> it.getId() == lesson.getId()).findAny().get();
                    return LessonInfoResponse.of(lesson, group);
                }
        );
        model.addAttribute("lessonsInfos", lessonsInfos);

        return "lesson/show-lessons-page";
    }

    @PostMapping("/group/{id}")
    public String createLesson(
            @PathVariable("id") long groupId,
            @RequestParam("lessonName") String lessonName,
            @RequestParam("lessonDescription") String lessonDescription,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date timeStart,
            @RequestParam("duration") int duration,
            @RequestParam("courseId") long courseId
    ) {
        lessonService.createLesson(groupId, lessonName, lessonDescription, timeStart, duration);
        return "redirect:/courses/" + courseId + "/teacher";
    }

    @PostMapping("/group/{id}/group-page")
    public String createLessonWithRedirectToGroupPage(
            @PathVariable("id") long groupId,
            @RequestParam("lessonName") String lessonName,
            @RequestParam("lessonDescription") String lessonDescription,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date timeStart,
            @RequestParam("duration") int duration
    ) {
        lessonService.createLesson(groupId, lessonName, lessonDescription, timeStart, duration);
        return "redirect:/groups/" + groupId;
    }
}
