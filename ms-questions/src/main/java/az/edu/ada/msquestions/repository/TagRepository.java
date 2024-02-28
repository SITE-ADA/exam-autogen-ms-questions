package az.edu.ada.msquestions.repository;

import az.edu.ada.msquestions.model.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
