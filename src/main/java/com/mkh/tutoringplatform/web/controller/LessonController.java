package com.mkh.tutoringplatform.web.controller;

import com.mkh.tutoringplatform.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    @PostMapping("/group/{id}")
    public String createLesson(
            @PathVariable("id") long groupId,
            @RequestParam("lessonName") String lessonName,
            @RequestParam("startDate")  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date timeStart,
            @RequestParam("duration") int duration,
            @RequestParam("courseId") long courseId
    ) {
        lessonService.createLesson(groupId, lessonName, timeStart, duration);
        return "redirect:/courses/" + courseId + "/teacher";
    }
}
