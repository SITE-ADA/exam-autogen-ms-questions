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
public class AnswerRequest {
    @NotNull
    private Long question;
    @NotNull
    private Set<String> text;
    @NotNull
    private Set<String> answerOptions;
}