package com.lms.backend.controller;

import com.lms.backend.model.Enrollment;
import com.lms.backend.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/user/{studentId}")
    public List<Enrollment> getEnrollmentsByUser(@PathVariable Long studentId) {
        return enrollmentService.getEnrollmentsByUserId(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<Enrollment> getEnrollmentsByCourse(@PathVariable Long courseId) {
        return enrollmentService.getEnrollmentsByCourseId(courseId);
    }

    @PostMapping("/enroll")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> enroll(@RequestParam Long studentId, @RequestParam Long courseId) {
        Enrollment enrollment = enrollmentService.enroll(studentId, courseId);
        return ResponseEntity.ok(enrollment);
    }

    @DeleteMapping("/unenroll")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> unenroll(@RequestParam Long studentId, @RequestParam Long courseId) {
        enrollmentService.unenroll(studentId, courseId);
        return ResponseEntity.ok("Unenrolled successfully");
    }

    @PostMapping("/complete")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> completeCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        enrollmentService.markCompleted(studentId, courseId);
        return ResponseEntity.ok("Marked as completed");
    }

    @GetMapping("/my-courses/{studentId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<Enrollment>> getMyCourses(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByUserId(studentId));
    }
}
