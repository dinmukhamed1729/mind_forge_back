package din.kz.mind_forge_back.model.dto;

import din.kz.mind_forge_back.model.entity.Difficulty;
import din.kz.mind_forge_back.model.entity.Tag;
import din.kz.mind_forge_back.model.entity.TestCase;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

public record TaskDTO(String title, String description, Difficulty difficulty, Set<Tag> tags, Set<TestCase> testCases,
                      int timeLimit, int memoryLimit, String inputFormat, String outputFormat) {
}
