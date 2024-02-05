package az.edu.ada.msquestions.service.impl;

import az.edu.ada.msquestions.model.entities.SubjectStatus;
import az.edu.ada.msquestions.repository.SubjectStatusRepository;
import az.edu.ada.msquestions.service.SubjectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SubjectStatusServiceImpl implements SubjectStatusService {
    private final SubjectStatusRepository subjectStatusRepository;

    @Autowired
    public SubjectStatusServiceImpl(SubjectStatusRepository subjectStatusRepository) {
        this.subjectStatusRepository = subjectStatusRepository;
    }

    @Override
    public SubjectStatus createSubjectStatus(SubjectStatus subjectStatus) {
        return subjectStatusRepository.save(subjectStatus);
    }

    @Override
    public List<SubjectStatus> getAllSubjectStatuses() {
        return subjectStatusRepository.findAll();
    }

    @Override
    public Optional<SubjectStatus> getSubjectStatusById(Long id) {
        return subjectStatusRepository.findById(id);
    }

    @Override
    public SubjectStatus updateSubjectStatus(Long id, SubjectStatus updatedSubjectStatus) {
        Optional<SubjectStatus> existingSubjectStatusOptional = subjectStatusRepository.findById(id);

        if (existingSubjectStatusOptional.isPresent()) {
            updatedSubjectStatus.setId(id);
            return subjectStatusRepository.save(updatedSubjectStatus);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public SubjectStatus patchSubjectStatus(Long id, Map<String, Object> updates) {
        Optional<SubjectStatus> optionalSubjectStatus = subjectStatusRepository.findById(id);
        if (optionalSubjectStatus.isEmpty()) {
            return null;
        }

        SubjectStatus subjectStatus = optionalSubjectStatus.get();
        applyPatchToSubjectStatus(subjectStatus, updates);
        subjectStatusRepository.save(subjectStatus);
        return subjectStatus;
    }

    private void applyPatchToSubjectStatus(SubjectStatus subjectStatus, Map<String, Object> updates) {
        Class<?> clazz = subjectStatus.getClass();
        updates.forEach((key, value) -> {
            try {
                Field field = clazz.getDeclaredField(key);
                field.setAccessible(true);
                field.set(subjectStatus, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle the exception, possibly logging a warning or throwing a custom exception
            }
        });
    }

    public void deleteSubjectStatus(Long id) {
        subjectStatusRepository.deleteById(id);
    }
}
