package din.kz.mind_forge_back.model.response;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private String difficulty;
    private Set<String> tags;
    private int timeLimit;
    private int memoryLimit;
    private String inputFormat;
    private String outputFormat;
    private Set<TestCaseResponse> publicTestCases; // Список публичных тест-кейсов
}
