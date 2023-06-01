package com.mkh.tutoringplatform.web.controller.student;

import com.mkh.tutoringplatform.domain.user.student.Lesson;
import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.domain.user.teacher.Subject;
import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.security.UserDetailsImpl;
import com.mkh.tutoringplatform.service.GroupService;
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
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final StudentService studentService;
    private final GroupService groupService;
    private final LessonService lessonService;

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


    @PostMapping("/create/group")
    public String submitStudents(@RequestParam("selectedStudentIds") List<Long> usersIds, @RequestParam("groupName")String name) {
        groupService.createGroup(usersIds, name, getAuthenticatedTeacher());
        return "redirect:/teacher/students";
    }

    @GetMapping("/groups")
    public String showGroups(Model model){
        model.addAttribute("groups", groupService.getGroups(getAuthenticatedTeacher()));
        return "teacher/show-groups-page";
    }

    @GetMapping("/group/{group_id}/lessons")
    public String getTheGroupLessonsPage(@PathVariable("group_id") long group_id, Model model){
        model.addAttribute("lessons", groupService.getLessons(group_id, getAuthenticatedTeacher()));
        model.addAttribute("group", groupService.getGroup(group_id));
        return "teacher/show-lessons-page";
    }

    @DeleteMapping("/group/{group_id}/lesson/delete/{lesson_id}")
    public String deleteLesson(@PathVariable("lesson_id") long lesson_id, @PathVariable("group_id") long group_id){
        groupService.deleteLesson(lesson_id, group_id);
        return "redirect:/teacher/group/"+group_id+"/lessons";
    }

    @DeleteMapping("/group/{group_id}")
    public String deleteGroup(@PathVariable("group_id") long group_id){
        groupService.deleteGroup(group_id);
        return "redirect:/teacher/groups";
    }

    @GetMapping("/create/{group_id}/lesson/")
    public String getCreateLessonPage(@PathVariable("group_id") long group_id, Model model){
        model.addAttribute("group_id", group_id);
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("subjects", getAuthenticatedTeacher().getSubjects());
        return "teacher/create-lesson-page";
    }



    @PostMapping("/create/{group_id}/lesson/")
    public String createLesson(@ModelAttribute("lesson") Lesson lesson, @PathVariable("group_id") long group_id){
        lessonService.save(lesson, group_id);
        return "redirect:/teacher/group/"+group_id+"/lessons";
    }

    @GetMapping("/lessons/today")
    public String getAllLessonsTodayPage(Model model){
        List<Lesson> lessons = lessonService.getLessonInAboutOneDay(getAuthenticatedTeacher());
        model.addAttribute("lessons", lessons);
        return "teacher/lessons-today-page";
    }

    @DeleteMapping("/student/{student_id}")
    public String deleteTeacher(@PathVariable("student_id") long student_id){
        studentService.deleteTeacher(student_id, getAuthenticatedTeacher().getId());
        return "redirect:/teacher/students";
    }

}
