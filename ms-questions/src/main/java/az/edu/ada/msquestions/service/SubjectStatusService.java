package az.edu.ada.msquestions.service;

import az.edu.ada.msquestions.model.entities.SubjectStatus;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SubjectStatusService {
    SubjectStatus createSubjectStatus(SubjectStatus subjectStatus);
    Optional<SubjectStatus> getSubjectStatusById(Long id);
    SubjectStatus updateSubjectStatus(Long id, SubjectStatus subjectStatus);
    SubjectStatus patchSubjectStatus(Long id, Map<String, Object> updates);
    void deleteSubjectStatus(Long id);
    List<SubjectStatus> getAllSubjectStatuses();
}
