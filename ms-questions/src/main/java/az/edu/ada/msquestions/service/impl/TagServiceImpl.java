package az.edu.ada.msquestions.service.impl;

import az.edu.ada.msquestions.model.entities.Tag;
import az.edu.ada.msquestions.repository.TagRepository;
import az.edu.ada.msquestions.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Optional<Tag> getTagById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public Tag updateTag(Long id, Tag updatedTag) {
        Optional<Tag> existingTagOptional = tagRepository.findById(id);

        if (existingTagOptional.isPresent()) {
            updatedTag.setId(id);
            return tagRepository.save(updatedTag);
        } else {
            return null;
        }
    }

    @Override
    public Tag patchTag(Long id, Map<String, Object> updates) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if (!optionalTag.isPresent()) {
            return null;
        }

        Tag tag = optionalTag.get();
        applyPatchToTag(tag, updates);
        tagRepository.save(tag);
        return tag;
    }

    private void applyPatchToTag(Tag tag, Map<String, Object> updates) {
        Class<?> clazz = tag.getClass();
        updates.forEach((key, value) -> {
            try {
                Field field = clazz.getDeclaredField(key);
                field.setAccessible(true);
                field.set(tag, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle the exception, possibly logging a warning or throwing a custom exception
            }
        });
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
