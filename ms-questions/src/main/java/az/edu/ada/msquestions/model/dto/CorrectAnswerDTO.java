package az.edu.ada.msquestions.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CorrectAnswerDTO {
    private Long id;
    private AnswerDTO answer;
}
