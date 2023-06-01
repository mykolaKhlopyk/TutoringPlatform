package com.mkh.tutoringplatform.web.controller.student;

import com.mkh.tutoringplatform.domain.user.student.Lesson;
import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.security.UserDetailsImpl;
import com.mkh.tutoringplatform.service.LessonService;
import com.mkh.tutoringplatform.service.StudentService;
import com.mkh.tutoringplatform.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final TeacherService teacherService;
    private final StudentService studentService;
    private final LessonService lessonService;

    private Student getAuthenticatedStudent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        return user.getStudent();
    }

    @GetMapping("/teachers/all")
    public String getAllTeachersPage(Model model) {
        List<User> usersTeachers = teacherService.getAllNewTeachersForStudent(getAuthenticatedStudent()).stream().map(Teacher::getUser).collect(Collectors.toList());
        List<User> requestedTeachers = studentService.getAllRequestedByStudent(getAuthenticatedStudent()).stream().map(Teacher::getUser).collect(Collectors.toList());

        model.addAttribute("usersTeachers", usersTeachers);
        model.addAttribute("requestedTeachers", requestedTeachers);
        return "student/search-new-teachers";
    }

    @GetMapping("/teachers/my")
    public String getMyTeachersPage(Model model) {
        List<User> usersTeachers = teacherService.getAllTeachersOfStudent(getAuthenticatedStudent()).stream().map(Teacher::getUser).collect(Collectors.toList());
        model.addAttribute("usersTeachers", usersTeachers);
        return "student/my-teachers";
    }

    @PatchMapping("/request/{id}")
    public String makeRequest(@PathVariable("id") long id) {
        studentService.requestTeacher(id, getAuthenticatedStudent());
        return "redirect:/student/teachers/all";
    }

    @PatchMapping("/cancel/request/{id}")
    public String canselRequest(@PathVariable("id") long id) {
        studentService.cancelRequest(id, getAuthenticatedStudent());
        return "redirect:/student/teachers/all";
    }

    @GetMapping("/teacher/{teacher_id}/lessons")
    public String getAllLessonsWithTheTeacherPage(@PathVariable("teacher_id")long teacher_id, Model model){
        List<Lesson> lessons = lessonService.getLessonByTeacherId(teacher_id, getAuthenticatedStudent().getId());
        model.addAttribute("lessons", lessons);
        model.addAttribute("teacher", teacherService.getById(teacher_id));
        return "student/lessons-of-teacher-page";
    }

    @GetMapping("/lessons/today")
    public String getAllLessonsTodayPage(Model model){
        List<Lesson> lessons = lessonService.getLessonInAboutOneDay(getAuthenticatedStudent());
        model.addAttribute("lessons", lessons);
        return "student/lessons-today-page";
    }

    @DeleteMapping("teacher/{teacher_id}")
    public String deleteTeacher(@PathVariable("teacher_id") long teacher_id){
        studentService.deleteTeacher(getAuthenticatedStudent().getId(), teacher_id);
        return "redirect:student/teachers/my";
    }



}

