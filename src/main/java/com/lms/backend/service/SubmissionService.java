package com.lms.backend.service;

import com.lms.backend.model.Question;
import com.lms.backend.model.Quiz;
import com.lms.backend.model.Submission;
import com.lms.backend.repository.QuestionRepository;
import com.lms.backend.repository.QuizRepository;
import com.lms.backend.repository.SubmissionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    public SubmissionService(SubmissionRepository submissionRepository, QuestionRepository questionRepository, QuizRepository quizRepository) {
        this.submissionRepository = submissionRepository;
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    public Submission saveSubmission(Submission submission) {
        int score = 0;

        Map<Long, String> answers = submission.getAnswers();

        if (submission.getQuiz() != null && answers != null) {
            Long quizId = submission.getQuiz().getId();
            List<Question> quizQuestions = questionRepository.findByQuizId(quizId);

            for (Question question : quizQuestions) {
                String correctAnswer = question.getCorrectAnswer();
                String userAnswer = answers.get(question.getId());

                if (correctAnswer != null && correctAnswer.equalsIgnoreCase(userAnswer)) {
                    score++;
                }
            }

            Quiz quiz = quizRepository.findById(quizId).orElse(null);
            submission.setQuiz(quiz);
            submission.setScore(score);
            submission.setSubmittedAt(LocalDateTime.now());
        }

        return submissionRepository.save(submission);
    }

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public Submission getSubmissionById(Long id) {
        return submissionRepository.findById(id).orElse(null);
    }
}
