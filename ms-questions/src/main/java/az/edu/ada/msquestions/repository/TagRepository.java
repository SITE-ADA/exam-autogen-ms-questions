package az.edu.ada.msquestions.repository;

import az.edu.ada.msquestions.model.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Set<Tag> findAllByIdIn(Set<Long> ids);
}
