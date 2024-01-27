package az.edu.ada.msquestions.repository;

import az.edu.ada.msquestions.model.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
