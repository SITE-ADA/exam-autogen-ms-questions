package az.edu.ada.msquestions.controller;

import az.edu.ada.msquestions.model.entities.CorrectAnswer;
import az.edu.ada.msquestions.model.request.CorrectAnswerRequest;
import az.edu.ada.msquestions.model.request.CorrectAnswersRequest;
import az.edu.ada.msquestions.service.CorrectAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/questions/correct-answers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CorrectAnswerController {
    private final CorrectAnswerService correctAnswerService;

    @PostMapping
    public ResponseEntity<List<CorrectAnswer>> createCorrectAnswer(@RequestBody CorrectAnswersRequest correctAnswersRequest) {
        List<CorrectAnswer> createdCorrectAnswer = correctAnswerService.createCorrectAnswer(correctAnswersRequest);
        return ResponseEntity.ok(createdCorrectAnswer);
    }

    @GetMapping
    public ResponseEntity<List<CorrectAnswer>> getAllCorrectAnswers() {
        List<CorrectAnswer> questions = correctAnswerService.getAllCorrectAnswers();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CorrectAnswer> getCorrectAnswerById(@PathVariable Long id) {
        return correctAnswerService.getCorrectAnswerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CorrectAnswer> updateCorrectAnswer(@PathVariable Long id, @RequestBody CorrectAnswer updatedCorrectAnswer) {
        CorrectAnswer updated = correctAnswerService.updateCorrectAnswer(id, updatedCorrectAnswer);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CorrectAnswer> patchCorrectAnswer(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        CorrectAnswer patchedCorrectAnswer = correctAnswerService.patchCorrectAnswer(id, updates);
        if (patchedCorrectAnswer != null) {
            return ResponseEntity.ok(patchedCorrectAnswer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCorrectAnswer(@PathVariable Long id) {
        correctAnswerService.deleteCorrectAnswer(id);
        return ResponseEntity.noContent().build();
    }
}
