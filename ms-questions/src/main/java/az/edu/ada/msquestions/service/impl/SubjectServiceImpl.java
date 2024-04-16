package az.edu.ada.msquestions.service.impl;

import az.edu.ada.msquestions.model.dto.SubjectDTO;
import az.edu.ada.msquestions.model.enums.ESubjectStatus;
import az.edu.ada.msquestions.repository.SubjectRepository;
import az.edu.ada.msquestions.service.SubjectService;
import az.edu.ada.msquestions.model.entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public Subject updateSubject(Long id, Subject updatedSubject) {
        Optional<Subject> existingSubjectOptional = subjectRepository.findById(id);

        if (existingSubjectOptional.isPresent()) {
            updatedSubject.setId(id);
            return subjectRepository.save(updatedSubject);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Subject patchSubject(Long id, Map<String, Object> updates) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (!optionalSubject.isPresent()) {
            return null;
        }

        Subject subject = optionalSubject.get();
        applyPatchToSubject(subject, updates);
        subjectRepository.save(subject);
        return subject;
    }

    private void applyPatchToSubject(Subject subject, Map<String, Object> updates) {
        Class<?> clazz = subject.getClass();
        updates.forEach((key, value) -> {
            try {
                Field field = clazz.getDeclaredField(key);
                field.setAccessible(true);
                var val = value;
                if(key.equals("subjectStatus")) {
                    val = ESubjectStatus.valueOf(value.toString());
                } else if(key.equals("userId")) {
                    val = Long.parseLong(value.toString());
                }
                field.set(subject, val);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle the exception, possibly logging a warning or throwing a custom exception
            }
        });
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    public List<SubjectDTO> findSubjectsByUserId(Long userId) {
        return subjectRepository.findByUserId(userId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private SubjectDTO convertToDto(Subject subject) {
        return new SubjectDTO(
                subject.getId(),
                subject.getName(),
                subject.getCrn(),
                subject.getTerm(),
                subject.getSubjectStatusId(),
                subject.getCourseObjectives(),
                subject.getDescription()
        );
    }

}
