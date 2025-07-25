package com.lms.backend.controller;

import com.lms.backend.model.Question;
import com.lms.backend.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/quiz/{quizId}")
    public List<Question> getQuestionsByQuiz(@PathVariable Long quizId) {
        return questionService.getQuestionsByQuiz(quizId);
    }

    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }
}
