package din.kz.mind_forge_back.model.dto;

import lombok.Data;

@Data
public class TestCaseDTO {
    private String inputData, expectedOutput;
    private boolean isPublic;
}
