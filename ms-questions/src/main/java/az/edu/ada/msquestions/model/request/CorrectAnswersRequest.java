package az.edu.ada.msquestions.model.request;

import az.edu.ada.msquestions.model.entities.CorrectAnswer;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class CorrectAnswersRequest {
    List<CorrectAnswerRequest> correctAnswerList;
}
