package az.edu.ada.msquestions.model.request;

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
public class QuestionsRequest {
    List<QuestionRequest> questionRequest;
}
