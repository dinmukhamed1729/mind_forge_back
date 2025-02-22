package din.kz.mind_forge_back.model.response;

import java.util.Set;

public record TaskResponse(Long id, String title, String description, String difficulty, Set<String> tags,
                           int timeLimit, int memoryLimit, String inputFormat, String outputFormat,
                           Set<TestCaseResponse> publicTestCases) {
}
