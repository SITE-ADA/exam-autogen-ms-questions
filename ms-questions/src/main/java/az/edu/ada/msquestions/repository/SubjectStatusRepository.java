package az.edu.ada.msquestions.repository;

import az.edu.ada.msquestions.model.entities.SubjectStatus;
import az.edu.ada.msquestions.model.enums.ESubjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectStatusRepository extends JpaRepository<SubjectStatus, Long> {
    SubjectStatus findBySubjectStatus(ESubjectStatus subjectStatus);
}
