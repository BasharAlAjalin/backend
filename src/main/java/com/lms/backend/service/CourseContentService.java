package com.lms.backend.service;

import com.lms.backend.model.Course;
import com.lms.backend.model.CourseContent;
import com.lms.backend.repository.CourseContentRepository;
import com.lms.backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseContentService {

    @Autowired
    private CourseContentRepository contentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public CourseContent uploadContent(Long courseId, String title, String type, String url) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        CourseContent content = new CourseContent(title, type, url, course);
        return contentRepository.save(content);
    }

    public List<CourseContent> getContentsByCourse(Long courseId) {
        return contentRepository.findByCourseId(courseId);
    }
}
