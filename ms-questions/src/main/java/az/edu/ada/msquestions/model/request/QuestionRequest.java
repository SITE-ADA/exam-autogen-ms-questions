package az.edu.ada.msquestions.model.request;

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
public class QuestionRequest {
    @NotNull
    private String text;
    @NotNull
    private String notes;
    @NotNull
    private Double defaultScore;
    @NotNull
    private Long questionTypeId;
    @NotNull
    private Long questionPoolId;
    @NotNull
    private Set<Long> tagsIds;
}
