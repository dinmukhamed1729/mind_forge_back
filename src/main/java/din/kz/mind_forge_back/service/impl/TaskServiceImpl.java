package din.kz.mind_forge_back.service.impl;

import din.kz.mind_forge_back.mapper.TaskMapper;
import din.kz.mind_forge_back.model.entity.Task;
import din.kz.mind_forge_back.model.request.CreateTaskRequest;
import din.kz.mind_forge_back.model.response.ShortTaskResponse;
import din.kz.mind_forge_back.repository.TaskRepository;
import din.kz.mind_forge_back.service.DifficultlyService;
import din.kz.mind_forge_back.service.TagService;
import din.kz.mind_forge_back.service.TaskService;
import din.kz.mind_forge_back.service.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TagService tagService;
    private final DifficultlyService difficultlyService;
    private final TestCaseService testCaseService;

    @Override
    public ShortTaskResponse createTask(CreateTaskRequest request) {
        var task = taskMapper.taskRequestToTask(request);
        setTaskDependencies(request, task);
        return taskMapper.toShortTaskResponse(taskRepository.save(task));
    }

    @Override
    public Page<ShortTaskResponse> getTasksWithRange(Pageable pageable) {
        return taskRepository.findAll(pageable).map(taskMapper::toShortTaskResponse);
    }

    @Override
    public Optional<Task> getByTitleWithPublicTestCases(String title) {
        return taskRepository.findByTitle(title)
                .map(task ->
                {
                    task.setTestCases(testCaseService.getTestCasesWhithPublicTestCases(task.getId()));
                    return task;
                });
    }

    private void setTaskDependencies(CreateTaskRequest request, Task task) {
        var difficulty = difficultlyService.findDifficultly(request.getDifficulty());
        var persistedTags = tagService.findOrCreateTags(request.getTags());

        task.setDifficulty(difficulty);
        task.setTags(persistedTags);
        task.getTestCases().forEach(t -> t.setTask(task));
    }
}
