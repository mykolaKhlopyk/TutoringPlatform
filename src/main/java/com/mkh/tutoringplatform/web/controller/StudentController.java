package com.mkh.tutoringplatform.web.controller;

import com.mkh.tutoringplatform.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/course/{id}/request/subscribe")
    public String getAllStudentsAskedForSubscribeForCourse(@PathVariable("id") long courseId, Model model) {
        var students = studentService.getAllStudentsAskedForSubscribeForCourse(courseId);
        model.addAllAttributes(Map.of(
                "students", students,
                "courseId", courseId
        ));
        return "student/all-requested-for-subscribe-students-page";
    }

    @GetMapping("/course/{id}/subscribed")
    public String getAllSubscribedStudentsForCourse(@PathVariable("id") long courseId, Model model) {
        var students = studentService.getAllStudentsFromCourse(courseId);
        model.addAllAttributes(Map.of(
                "students", students,
                "courseId", courseId
        ));
        return "student/all-subscribed-students-to-course-page";
    }
}
