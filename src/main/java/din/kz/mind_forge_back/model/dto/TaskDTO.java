package din.kz.mind_forge_back.model.dto;

import din.kz.mind_forge_back.model.entity.Difficulty;
import din.kz.mind_forge_back.model.entity.Tag;
import din.kz.mind_forge_back.model.entity.TestCase;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;
import java.util.Set;
@AllArgsConstructor
@Data
public final class TaskDTO {
    private final String title;
    private final String description;
    private final Difficulty difficulty;
    private final Set<Tag> tags;
    private final Set<TestCase> testCases;
    private final int timeLimit;
    private final int memoryLimit;
    private final String inputFormat;
    private final String outputFormat;
}
