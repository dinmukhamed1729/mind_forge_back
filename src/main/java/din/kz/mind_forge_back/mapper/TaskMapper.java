package din.kz.mind_forge_back.mapper;

import din.kz.mind_forge_back.model.entity.Task;
import din.kz.mind_forge_back.model.request.CreateTaskRequest;
import din.kz.mind_forge_back.model.response.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TestCaseMapper.class, TagMapper.class})
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "difficulty", ignore = true)
    Task taskRequestToTask(CreateTaskRequest request);

    @Mapping(target = "difficulty", expression = "java(task.getDifficulty().getLevel())")
    @Mapping(target = "tags", source = "tags", qualifiedByName = "toTagNames")
    TaskResponse toResponse(Task task);

}

