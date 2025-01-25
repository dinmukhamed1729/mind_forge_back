package din.kz.mind_forge_back.model.request;

import din.kz.mind_forge_back.model.dto.TestCaseDTO;
import lombok.Data;

import java.util.Set;

@Data
public class CreateTaskRequest {
    private String title, description, inputFormat, outputFormat, difficulty;
    private int timelimit, memorylimit;
    private Set<TestCaseDTO> testCases;
    private Set<String> tags;

}
