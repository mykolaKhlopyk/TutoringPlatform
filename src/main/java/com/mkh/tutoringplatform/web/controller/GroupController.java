package com.mkh.tutoringplatform.web.controller;

import com.mkh.tutoringplatform.service.GroupService;
import com.mkh.tutoringplatform.service.LessonService;
import com.mkh.tutoringplatform.service.StudentService;
import com.mkh.tutoringplatform.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mkh.tutoringplatform.web.utils.ControllerUtils.getAuthenticatedUser;
import static com.mkh.tutoringplatform.web.utils.ControllerUtils.isAvailableResourceForUser;

@Controller
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    private final TeacherService teacherService;

    private final LessonService lessonService;

    private final StudentService studentService;

    @GetMapping("/teacher")
    public String getTeacherGroups(
            @AuthenticationPrincipal UserDetails userDetails,
            Model model
    ) {
        var teacherId = getAuthenticatedUser(userDetails).getUserInitiatorId();
        var groups = groupService.getTeacherGroups(teacherId);
        model.addAttribute("groups", groups);
        return "group/show-groups-page";
    }

    @GetMapping("/{id}")
    public String getGroupById(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") long groupId,
            Model model
    ) {
        var teacherId = getAuthenticatedUser(userDetails).getUserInitiatorId();

        var group = groupService.getGroupById(groupId);
        var groupOwnerId = teacherService.getCourseOwnerId(group.getCourseId());

        isAvailableResourceForUser(groupOwnerId, teacherId);

        var lessons = lessonService.getLessonsFromGroup(groupId);
        var students = studentService.getAllStudentsFromGroup(groupId);

        model.addAttribute("group", group);
        model.addAttribute("lessons", lessons);
        model.addAttribute("students", students);
        return "group/group-page";
    }

    @PostMapping("/course/{id}")
    public String createGroup(
            @PathVariable("id") long courseId,
            @RequestParam("selectedStudentIds") List<Long> studentsIds,
            @RequestParam("groupName") String name
    ) {
        groupService.createGroup(studentsIds, name, courseId);
        return "redirect:/courses/" + courseId + "/teacher";
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(
            @PathVariable("id") long groupId,
            @RequestParam("courseId") long courseId
    ) {
        groupService.deleteGroup(groupId);
        return "redirect:/courses/" + courseId + "/teacher";
    }
}
