package com.mkh.tutoringplatform.web.controller.student;

import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.security.UserDetailsImpl;
import com.mkh.tutoringplatform.service.StudentService;
import com.mkh.tutoringplatform.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final TeacherService teacherService;

    private final StudentService studentService;

    private Student getAuthenticatedStudent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        return user.getStudent();
    }

    @GetMapping("/")
    public String getAllTeachersPage(Model model) {
        List<User> usersTeachers = teacherService.getAllNewTeachersForStudent(getAuthenticatedStudent()).stream().map(Teacher::getUser).collect(Collectors.toList());
        List<User> requestedTeachers = studentService.getAllRequestedByStudent(getAuthenticatedStudent()).stream().map(Teacher::getUser).collect(Collectors.toList());

        model.addAttribute("usersTeachers", usersTeachers);
        model.addAttribute("requestedTeachers", requestedTeachers);
        return "student/search-new-teachers";
    }

    @PatchMapping("/request/{id}")
    public String makeRequest(@PathVariable("id") long id) {
        studentService.requestTeacher(id, getAuthenticatedStudent());
        return "redirect:/student/";
    }

    @PatchMapping("/cancel/request/{id}")
    public String canselRequest(@PathVariable("id") long id) {
        studentService.cancelRequest(id, getAuthenticatedStudent());
        return "redirect:/student/";
    }

}

