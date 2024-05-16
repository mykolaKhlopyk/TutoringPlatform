package com.mkh.tutoringplatform.web.controller;

import com.mkh.tutoringplatform.domain.user.Course;
import com.mkh.tutoringplatform.service.CourseService;
import com.mkh.tutoringplatform.service.GroupService;
import com.mkh.tutoringplatform.service.StudentService;
import com.mkh.tutoringplatform.web.response.CourseInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

import static com.mkh.tutoringplatform.domain.user.user.User.UserRole.TEACHER;
import static com.mkh.tutoringplatform.web.utils.ControllerUtils.getAuthenticatedUser;
import static com.mkh.tutoringplatform.web.utils.ControllerUtils.isAvailableResourceForUser;

@Controller
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    private final GroupService groupService;

    private final StudentService studentsService;

    @GetMapping
    public String getAllCourses(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        var courses = courseService.getAllCourses();

        var isAvailableToJoin = userDetails != null && getAuthenticatedUser(userDetails).getUserRole() == TEACHER;
        var coursesIdNotToShow = coursesNotToShow(userDetails);
        var result = courses.stream()
                .filter(course -> !coursesIdNotToShow.contains(course.getId()))
                .map(this::mapCourseToCourseInfoResponse)
                .toList();
        model.addAllAttributes(Map.of(
                "auth", false,
                "courses", result,
                "isAvailableToJoin", isAvailableToJoin
        ));
        return "course/all-courses-page";
    }

    @PostMapping
    public String saveCourse(String courseName, String courseDescription, @AuthenticationPrincipal UserDetails userDetails) {
        var teacherId = getAuthenticatedUser(userDetails).getUserInitiatorId();
        courseService.saveCourse(courseName, courseDescription, teacherId);
        return "redirect:/courses/teacher";
    }

    @GetMapping("/teacher")
    //Role
    public String getTeacherCourses(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        var teacherId = getAuthenticatedUser(userDetails).getUserInitiatorId();
        var courses = courseService.getTeacherCourses(teacherId);
        var courseResponse = courses.stream()
                .map(this::mapCourseToCourseInfoResponse)
                .toList();

        model.addAllAttributes(Map.of(
                "auth", true,
                "courses", courseResponse
        ));
        return "course/all-courses-page";
    }

    @GetMapping("/student")
    //Role
    public String getStudentCourses(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        var studentId = getAuthenticatedUser(userDetails).getUserInitiatorId();
        var courses = courseService.getStudentCourses(studentId);
        var courseResponse = courses.stream()
                .map(this::mapCourseToCourseInfoResponse)
                .toList();
        model.addAttribute("courses", courseResponse);
        model.addAttribute("auth", true);
        return "course/all-courses-page";
    }

    @GetMapping("/{id}/teacher")
    //Role
    public String getCourseForOwner(@PathVariable("id") long courseId, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        var teacherId = getAuthenticatedUser(userDetails).getUserInitiatorId();
        var course = courseService.getCourseById(courseId);

        isAvailableResourceForUser(course.getTeacherId(), teacherId);

        var groups = groupService.getGroupsFromCourse(courseId);
        var students = studentsService.getAllStudentsFromCourse(courseId);
        model.addAllAttributes(Map.of(
                "course", course,
                "groups", groups,
                "students", students
        ));

        return "course/course-for-owner-page";
    }

    @PutMapping("/{id}")
    public String updateCourse(@PathVariable("id") long courseId,
                               @RequestParam String literature,
                               @RequestParam String task,
                               @RequestParam String link,
                               RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute("messageSaved", true);
        courseService.updateCourse(courseId, literature, task, link);
        return "redirect:/courses/" + courseId + "/teacher";
    }

    @PatchMapping("/{id}/request/subscribe")
    //TODO role for student
    public String sendRequestForSubscribeStudentToCourse(
            @PathVariable("id") long courseId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        var user = getAuthenticatedUser(userDetails);
        courseService.sendRequestForSubscribeStudentToCourse(user.getUserInitiatorId(), courseId);
        return "redirect:/courses";
    }

    @PatchMapping("/{id}/request/subscribe/agree/{studentId}")
    //TODO role for student
    public String agreeRequestForSubscribeStudentToCourse(
            @PathVariable("id") long courseId,
            @PathVariable("studentId") long studentId
    ) {
        courseService.agreeRequestForSubscribeStudentToCourse(courseId, studentId);
        return "redirect:/courses";
    }

    @PatchMapping("/{id}/request/subscribe/disagree/{studentId}")
    //TODO role for student
    public String disagreeRequestForSubscribeStudentToCourse(
            @PathVariable("id") long courseId,
            @PathVariable("studentId") long studentId
    ) {
        courseService.disagreeRequestForSubscribeStudentToCourse(courseId, studentId);
        return "redirect:/courses";
    }

    //TODO
//    @PatchMapping("/{id}/request/unsubscribe")
//    public String unsubscribeToCourse(String courseId) {
////        courseService.
//    }

    private List<Long> coursesNotToShow(UserDetails userDetails) {
        if (userDetails == null) {
            return List.of();
        }
        var user = getAuthenticatedUser(userDetails);
        return switch (user.getUserRole()) {
            case STUDENT -> courseService.getStudentCourses(user.getUserInitiatorId()).stream()
                    .map(Course::getId).toList();
            case TEACHER -> courseService.getTeacherCourses(user.getUserInitiatorId()).stream()
                    .map(Course::getId).toList();
        };
    }

    //TODO
    private CourseInfoResponse mapCourseToCourseInfoResponse(Course course) {
        return CourseInfoResponse.builder()
                .name(course.getName())
                .description(course.getDescription())
                .teacherName("Test")
                .courseId(course.getId())
                .showForOwner(true)
                .build();
    }
}
