package az.edu.ada.msquestions.model.entities;

import az.edu.ada.msquestions.model.enums.ESubjectStatus;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Valid
@Builder
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "course_objectives")
    private String courseObjectives;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "status")
    private Long subjectStatusId;

    @Column(name = "crn", unique = true)
    private String crn;

    @Column(name = "term", unique = true)
    private String term;
}