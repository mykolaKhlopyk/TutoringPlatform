package com.mkh.tutoringplatform.web.controller.student;

import com.mkh.tutoringplatform.domain.user.student.Group;
import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.security.UserDetailsImpl;
import com.mkh.tutoringplatform.service.GroupService;
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
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    private final GroupService groupService;
    @GetMapping("/home")
    public String getHomePage() {
        return "teacher/teacher-page";
    }


    private Teacher getAuthenticatedTeacher() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        return user.getTeacher();
    }

    @GetMapping("/requested")
    public String getNotConfirmedStudentsPage(Model model) {
        model.addAttribute("notConfirmedStudents", teacherService.getAllNotConfirmedStudent(getAuthenticatedTeacher()).stream().map(Student::getUser).collect(Collectors.toList()));
        return "teacher/not-confirmed-students-page";
    }
    @PatchMapping("/request/agree/{id}")
    public String agreeStudent(@PathVariable("id") long id) {
        teacherService.agreeStudentById(id, getAuthenticatedTeacher());
        return "redirect:/teacher/requested";
    }

    @PatchMapping("/request/disagree/{id}")
    public String disagreeStudent(@PathVariable("id") long id) {
        teacherService.disagreeStudentById(id, getAuthenticatedTeacher());
        return "redirect:/teacher/requested";
    }

    @GetMapping("/students")
    public String getAllTeachersConfirmedStudentsPage(Model model){
        model.addAttribute("confirmedStudents", teacherService.getAllConfirmedStudent(getAuthenticatedTeacher()).stream().map(Student::getUser).collect(Collectors.toList()));
        return "teacher/confirmed-students-page";
    }

    @GetMapping("/create/group")
    public String createGroupPage(Model model){
        model.addAttribute("confirmedStudents", teacherService.getAllConfirmedStudent(getAuthenticatedTeacher()).stream().map(Student::getUser).collect(Collectors.toList()));
        return "teacher/create-group-page";
    }


    @PostMapping("/submitStudents")
    public String submitStudents(@RequestParam("selectedStudentIds") List<Long> usersIds, @RequestParam("groupName")String name) {
        groupService.createGroup(usersIds, name, getAuthenticatedTeacher());
        return "redirect:/teacher/students";
    }

    @GetMapping("/groups")
    public String showGroups(Model model){
        model.addAttribute("groups", groupService.getGroups(getAuthenticatedTeacher()));
        return "teacher/show-groups";
    }
}
