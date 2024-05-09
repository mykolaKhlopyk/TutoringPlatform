package com.mkh.tutoringplatform.web.controller.student;

import com.mkh.tutoringplatform.domain.user.Course;
import com.mkh.tutoringplatform.domain.user.Lesson;
import com.mkh.tutoringplatform.domain.user.Student;
import com.mkh.tutoringplatform.domain.user.Teacher;
import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.security.UserDetailsImpl;
import com.mkh.tutoringplatform.service.CourseService;
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

    private final CourseService courseService;

    private Student getAuthenticatedStudent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        return (Student) user.getUserInitiator();
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
    public String getAllLessonsWithTheTeacherPage(@PathVariable("teacher_id") long teacher_id, Model model) {
        List<Lesson> lessons = lessonService.getLessonsByTeacherId(teacher_id, getAuthenticatedStudent().getId());
        model.addAttribute("lessons", lessons);
        model.addAttribute("teacher", teacherService.getById(teacher_id));
        return "student/lessons-of-teacher-page";
    }

    @GetMapping("/lessons/today")
    public String getAllLessonsTodayPage(Model model) {
        List<Lesson> lessons = lessonService.getTodayLessonForStudent(getAuthenticatedStudent());
        model.addAttribute("lessons", lessons);
        return "student/lessons-today-page";
    }

    @DeleteMapping("/teacher/{teacher_id}")
    public String deleteTeacher(@PathVariable("teacher_id") long teacher_id) {
        studentService.deleteTeacher(getAuthenticatedStudent().getId(), teacher_id);
        return "redirect:/student/teachers/my";
    }

    @GetMapping("/courses/all")
    public String getAllCourses(Model model) {
        Student student = getAuthenticatedStudent();
        List<Course> studentCourses = studentService.getStudentCourses(student.getId());
        List<Course> courses = courseService.getAllCourses().stream()
                .filter(course -> !(studentCourses.stream().map(Course::getId).collect(Collectors.toList()).contains(course.getId())))
                .collect(Collectors.toList());
        model.addAttribute("courses", courses);
        return "student/show-all-courses-page";
    }

    @GetMapping("/course/{course_id}")
    public String getCourse(@PathVariable("course_id") long courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        model.addAttribute("course", course);
        return "course/student-course-page";
    }

    @PatchMapping("/course/{course_id}")
    public String joinToCourse(@PathVariable("course_id") long courseId) {
        Student student = getAuthenticatedStudent();
        studentService.joinStudentToCourse(student.getId(), courseId);
        return "redirect:/student/courses/all";
    }

    @GetMapping("/courses/my")
    public String getMyCourses(Model model) {
        Student student = getAuthenticatedStudent();
        List<Course> courses = studentService.getStudentCourses(student.getId());
        model.addAttribute("courses", courses);
        return "student/show-all-mycourses";
    }

    @DeleteMapping("/course/{course_id}")
    public String leaveCourse(@PathVariable("course_id") long course_id) {
        studentService.leaveStudentFromCourse(getAuthenticatedStudent().getId(), course_id);
        return "redirect:student/teachers/my";
    }
}
