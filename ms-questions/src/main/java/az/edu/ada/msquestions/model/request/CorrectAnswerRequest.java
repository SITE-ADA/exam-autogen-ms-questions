package az.edu.ada.msquestions.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class CorrectAnswerRequest {

    @NotNull
    private Long question;
    @NotNull
    private Long answer;
}
