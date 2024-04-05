package az.edu.ada.msquestions.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCountDTO {
    private Long questionPoolId;
    private Long count;
}