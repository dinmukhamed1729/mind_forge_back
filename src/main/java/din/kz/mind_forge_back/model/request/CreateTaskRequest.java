package din.kz.mind_forge_back.model.request;

import din.kz.mind_forge_back.model.dto.TestCaseDTO;

import java.util.Set;

public record CreateTaskRequest(String title, String description, String inputFormat, String outputFormat,
                                String difficulty, int timeLimit, int memoryLimit, Set<TestCaseDTO> testCases,
                                Set<String> tags) { }
