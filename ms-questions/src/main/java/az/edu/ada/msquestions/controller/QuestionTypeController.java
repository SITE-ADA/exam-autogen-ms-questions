package az.edu.ada.msquestions.controller;

import az.edu.ada.msquestions.model.entities.QuestionType;
import az.edu.ada.msquestions.service.QuestionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions/question-type")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionTypeController {
    private final QuestionTypeService questionTypeService;

    @PostMapping
    public ResponseEntity<QuestionType> createQuestionType(@RequestBody QuestionType questionType) {

        QuestionType createdQuestionType =  questionTypeService.createQuestionType(questionType);
        return ResponseEntity.ok(createdQuestionType);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionType> getQuestionTypeById(@PathVariable Long id) {
        return questionTypeService.getQuestionTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<QuestionType>> getAllQuestionTypes() {
        List<QuestionType> questionTypes = questionTypeService.getAllQuestionTypes();
        return ResponseEntity.ok(questionTypes);
    }

    @PutMapping("/{id}")
    public QuestionType updateQuestionType(@PathVariable Long id, @RequestBody QuestionType questionType) {
        return questionTypeService.updateQuestionType(id, questionType);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<QuestionType> patchQuestionType(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        QuestionType patchedQuestionType = questionTypeService.patchQuestionType(id, updates);
        if (patchedQuestionType != null) {
            return ResponseEntity.ok(patchedQuestionType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionType(@PathVariable Long id) {
        questionTypeService.deleteQuestionType(id);
        return ResponseEntity.noContent().build();
    }
}
