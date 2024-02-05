package az.edu.ada.msquestions.controller;

import az.edu.ada.msquestions.model.entities.QuestionPool;
import az.edu.ada.msquestions.service.QuestionPoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/questions/question-pool")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)

public class QuestionPoolController {
    private final QuestionPoolService questionPoolService;

    @PostMapping
    public ResponseEntity<QuestionPool> createQuestionPool(@RequestBody QuestionPool questionPool) {
        QuestionPool createdQuestionPool = questionPoolService.createQuestionPool(questionPool);
        return ResponseEntity.ok(createdQuestionPool);
    }

    @GetMapping
    public ResponseEntity<List<QuestionPool>> getAllQuestionPools() {
        List<QuestionPool> questions = questionPoolService.getAllQuestionPools();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionPool> getQuestionPoolById(@PathVariable Long id) {
        return questionPoolService.getQuestionPoolById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionPool> updateQuestionPool(@PathVariable Long id, @RequestBody QuestionPool updatedQuestionPool) {
        QuestionPool updated = questionPoolService.updateQuestionPool(id, updatedQuestionPool);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<QuestionPool> patchQuestionPool(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        QuestionPool patchedQuestionPool = questionPoolService.patchQuestionPool(id, updates);
        if (patchedQuestionPool != null) {
            return ResponseEntity.ok(patchedQuestionPool);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionPool(@PathVariable Long id) {
        questionPoolService.deleteQuestionPool(id);
        return ResponseEntity.noContent().build();
    }
}
