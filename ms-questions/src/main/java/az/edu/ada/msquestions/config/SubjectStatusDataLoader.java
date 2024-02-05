package az.edu.ada.msquestions.config;

import az.edu.ada.msquestions.model.entities.SubjectStatus;
import az.edu.ada.msquestions.model.enums.ESubjectStatus;
import az.edu.ada.msquestions.repository.SubjectStatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SubjectStatusDataLoader implements CommandLineRunner {
    private final SubjectStatusRepository subjectStatusRepository;

    public SubjectStatusDataLoader(SubjectStatusRepository subjectStatusRepository) {
        this.subjectStatusRepository = subjectStatusRepository;
    }

    @Override
    public void run(String... args) {
        for (ESubjectStatus eSubjectStatus : ESubjectStatus.values()) {
            SubjectStatus subjectStatus = subjectStatusRepository.findBySubjectStatus(eSubjectStatus);
            if (subjectStatus == null) {
                subjectStatus = SubjectStatus.builder()
                        .subjectStatus(eSubjectStatus)
                        .build();
                subjectStatusRepository.save(subjectStatus);
            }
        }
    }
}
