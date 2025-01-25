package din.kz.mind_forge_back.mapper;

import din.kz.mind_forge_back.model.dto.TestCaseDTO;
import din.kz.mind_forge_back.model.entity.Difficulty;
import din.kz.mind_forge_back.model.entity.Tag;
import din.kz.mind_forge_back.model.entity.Task;
import din.kz.mind_forge_back.model.entity.TestCase;
import din.kz.mind_forge_back.model.request.CreateTaskRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {TestCaseMapper.class, TagMapper.class})
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "testCases", source = "testCases", qualifiedByName = "testCaseDtoToTestCaseSet")
    @Mapping(target = "tags", source = "tags", qualifiedByName = "stringToTagSet")
    @Mapping(target = "difficulty", source = "difficulty", qualifiedByName = "stringToDifficulty")
    Task taskRequestToTask(CreateTaskRequest request);

    @Named("testCaseDtoToTestCaseSet")
    default Set<TestCase> testCaseDtoToTestCaseSet(Set<TestCaseDTO> testCaseDTOs) {
        if (testCaseDTOs == null) return new HashSet<>();
        return testCaseDTOs.stream()
                .map(dto -> {
                    TestCase testCase = new TestCase();
                    testCase.setInputData(dto.getInputData());
                    testCase.setExpectedOutput(dto.getExpectedOutput());
                    testCase.setPublic(dto.isPublic());
                    return testCase;
                })
                .collect(Collectors.toSet());
    }

    @Named("stringToTagSet")
    default Set<Tag> stringToTagSet(Set<String> tagNames) {
        if (tagNames == null) return new HashSet<>();
        return tagNames.stream()
                .map(name -> {
                    Tag tag = new Tag();
                    tag.setName(name);
                    return tag;
                })
                .collect(Collectors.toSet());
    }

    @Named("stringToDifficulty")
    default Difficulty stringToDifficulty(String level) {
        if (level == null || level.isBlank()) return null;
        Difficulty difficulty = new Difficulty();
        difficulty.setLevel(level);
        return difficulty;
    }
}

