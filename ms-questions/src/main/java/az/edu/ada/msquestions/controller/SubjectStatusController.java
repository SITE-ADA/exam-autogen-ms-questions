package az.edu.ada.msquestions.controller;

import az.edu.ada.msquestions.model.entities.SubjectStatus;
import az.edu.ada.msquestions.service.SubjectStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions/subject-status")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SubjectStatusController {
    private final SubjectStatusService subjectStatusService;

    @PostMapping
    public ResponseEntity<SubjectStatus> createSubjectStatus(@RequestBody SubjectStatus subjectStatus) {

        SubjectStatus createdSubjectStatus =  subjectStatusService.createSubjectStatus(subjectStatus);
        return ResponseEntity.ok(createdSubjectStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectStatus> getSubjectStatusById(@PathVariable Long id) {
        return subjectStatusService.getSubjectStatusById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<SubjectStatus>> getAllSubjectStatuses() {
        List<SubjectStatus> subjectStatuses = subjectStatusService.getAllSubjectStatuses();
        return ResponseEntity.ok(subjectStatuses);
    }

    @PutMapping("/{id}")
    public SubjectStatus updateSubjectStatus(@PathVariable Long id, @RequestBody SubjectStatus subjectStatus) {
        return subjectStatusService.updateSubjectStatus(id, subjectStatus);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SubjectStatus> patchSubjectStatus(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        SubjectStatus patchedSubjectStatus = subjectStatusService.patchSubjectStatus(id, updates);
        if (patchedSubjectStatus != null) {
            return ResponseEntity.ok(patchedSubjectStatus);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubjectStatus(@PathVariable Long id) {
        subjectStatusService.deleteSubjectStatus(id);
        return ResponseEntity.noContent().build();
    }
}
