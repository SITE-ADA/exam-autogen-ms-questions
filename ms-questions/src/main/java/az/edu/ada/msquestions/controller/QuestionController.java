package az.edu.ada.msquestions.controller;

import az.edu.ada.msquestions.model.dto.QuestionCountDTO;
import az.edu.ada.msquestions.model.dto.QuestionDTO;
import az.edu.ada.msquestions.model.dto.QuestionTestDTO;
import az.edu.ada.msquestions.model.entities.Question;
import az.edu.ada.msquestions.model.request.QuestionRequest;
import az.edu.ada.msquestions.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/{questionPoolId}/forTest")
    public QuestionTestDTO getQuestionIdForTest(@PathVariable Long questionPoolId) {
        return  questionService.getQuestionDTOForMsTest(questionPoolId);
    }

    @GetMapping("/pool/{questionPoolId}")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByQuestionPoolId(@PathVariable Long questionPoolId) {
        List<QuestionDTO> questionDTOs = questionService.getQuestionsByQuestionPoolId(questionPoolId);
        if (questionDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(questionDTOs);
        }
    }

    @GetMapping("/count-by-pool")
    public ResponseEntity<List<QuestionCountDTO>> getQuestionCountsByPool() {
        List<QuestionCountDTO> counts = questionService.getQuestionCountsByPool();
        return ResponseEntity.ok(counts);
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionRequest questionRequest) {
        Question createdQuestion = questionService.createQuestion(questionRequest);
        return ResponseEntity.ok(createdQuestion);
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question updatedQuestion) {
        Question updated = questionService.updateQuestion(id, updatedQuestion);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Question> patchQuestion(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Question patchedQuestion = questionService.patchQuestion(id, updates);
        if (patchedQuestion != null) {
            return ResponseEntity.ok(patchedQuestion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
