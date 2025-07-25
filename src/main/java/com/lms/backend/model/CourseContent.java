package com.lms.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "course_contents")
public class CourseContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String type; // "PDF" or "VIDEO"

    private String url; // Can be local path or external link

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public CourseContent() {}

    public CourseContent(String title, String type, String url, Course course) {
        this.title = title;
        this.type = type;
        this.url = url;
        this.course = course;
    }

    // Getters & Setters...
    public Long getId() { return id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public Course getCourse() { return course; }

    public void setCourse(Course course) { this.course = course; }
}
