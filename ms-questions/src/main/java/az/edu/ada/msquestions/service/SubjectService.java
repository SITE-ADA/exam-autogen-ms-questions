package az.edu.ada.msquestions.service;

import az.edu.ada.msquestions.model.dto.SubjectDTO;
import az.edu.ada.msquestions.model.entities.Subject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SubjectService {
    Subject createSubject(Subject address);
    List<Subject> getAllSubjects();
    Optional<Subject> getSubjectById(Long id);
    List<SubjectDTO> findSubjectsByUserId(Long userId);
    Subject updateSubject(Long id, Subject updatedSubject);
    Subject patchSubject(Long id, Map<String, Object> updates);
    void deleteSubject(Long id);
}
