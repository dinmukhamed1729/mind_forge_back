package din.kz.mind_forge_back.service.impl;

import din.kz.mind_forge_back.exception.TaskNotFoundException;
import din.kz.mind_forge_back.mapper.TaskMapper;
import din.kz.mind_forge_back.model.entity.Task;
import din.kz.mind_forge_back.model.entity.TestCase;
import din.kz.mind_forge_back.model.request.CreateTaskRequest;
import din.kz.mind_forge_back.model.response.ShortTaskResponse;
import din.kz.mind_forge_back.repository.TaskRepository;
import din.kz.mind_forge_back.service.DifficultlyService;
import din.kz.mind_forge_back.service.TagService;
import din.kz.mind_forge_back.service.TaskService;
import din.kz.mind_forge_back.service.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

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
    @Cacheable(value = "taskWithPublicTestCases", key = "#title")
    public Optional<Task> getByTitleWithPublicTestCases(String title) {
        System.out.println("getByTitleWithPublicTestCases " + title);
        Optional<Task> task1 = taskRepository.findByTitle(title)
                .map(task ->
                {
                    task.setTestCases(testCaseService.getTestCasesWhithPublicTestCases(task.getId()));
                    return task;
                });
        System.out.println("getByTitleWithPublicTestCases " + task1.orElse(null));
        return task1;
    }

    @Override
    public Set<TestCase> getTaskTestCases(String taskName) {
        return taskRepository.findByTitle(taskName)
                .orElseThrow(TaskNotFoundException::new)
                .getTestCases();
    }

    private void setTaskDependencies(CreateTaskRequest request, Task task) {
        var difficulty = difficultlyService.findDifficultly(request.getDifficulty());
        var persistedTags = tagService.findOrCreateTags(request.getTags());

        task.setDifficulty(difficulty);
        task.setTags(persistedTags);
        task.getTestCases().forEach(t -> t.setTask(task));
    }
}
