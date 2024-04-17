package az.edu.ada.msquestions.service;

import az.edu.ada.msquestions.model.entities.Tag;
import az.edu.ada.msquestions.model.request.TagRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TagService {
    Tag createTag(TagRequest tagRequest);
    Optional<Tag> getTagById(Long id);
    Tag updateTag(Long id, Tag tag);
    Tag patchTag(Long id, Map<String, Object> updates);
    void deleteTag(Long id);
    List<Tag> getAllTags();
}
