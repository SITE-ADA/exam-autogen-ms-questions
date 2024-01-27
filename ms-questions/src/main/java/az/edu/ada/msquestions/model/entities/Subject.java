package az.edu.ada.msquestions.model.entities;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ESubjectStatus subjectStatus;

    @Column(name = "crn", unique = true)
    private String crn;

    @Column(name = "term", unique = true)
    private String term;
}