package com.mkh.tutoringplatform.web.controller;

import com.mkh.tutoringplatform.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/course/{id}")
    public String createGroup(
            @PathVariable("id") long courseId,
            @RequestParam("selectedStudentIds") List<Long> studentsIds,
            @RequestParam("groupName") String name
    ) {
        groupService.createGroup(studentsIds, name, courseId);
        return "redirect:/courses/" + courseId + "/teacher";
    }
}
