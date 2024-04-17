package az.edu.ada.msquestions.model.request;

import az.edu.ada.msquestions.model.entities.Question;
import az.edu.ada.msquestions.model.entities.Subject;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class TagRequest {
    @NotNull
    private String name;
    @NotNull
    private Long subjectId;
}
