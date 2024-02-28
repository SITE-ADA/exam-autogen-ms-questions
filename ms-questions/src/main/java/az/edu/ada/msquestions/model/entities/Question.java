package az.edu.ada.msquestions.model.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Valid
@Builder
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    private String notes;

    private Double defaultScore;

    @ManyToOne
    @JoinColumn(name = "question_type_id")
    private QuestionType questionType;

    @ManyToOne
    @JoinColumn(name = "question_pool_id")
    private QuestionPool questionPool;

    @OneToOne
    private CorrectAnswer correctAnswer;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers;

    @ManyToMany
    @JoinTable(
            name = "QuestionTag",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;
}
