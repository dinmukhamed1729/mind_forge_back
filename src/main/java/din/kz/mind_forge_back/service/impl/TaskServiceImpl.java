package din.kz.mind_forge_back.service.impl;

import din.kz.mind_forge_back.mapper.TaskMapper;
import din.kz.mind_forge_back.model.request.CreateTaskRequest;
import din.kz.mind_forge_back.model.response.TaskResponse;
import din.kz.mind_forge_back.repository.TaskRepository;
import din.kz.mind_forge_back.service.DifficultlyService;
import din.kz.mind_forge_back.service.TagService;
import din.kz.mind_forge_back.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TagService tagService;
    private final DifficultlyService difficultlyService;

    @Override
    public TaskResponse createTask(CreateTaskRequest request) {
        var task = taskMapper.taskRequestToTask(request);
        var difficulty = difficultlyService.findDifficultly(request.getDifficulty());
        var persistedTags = tagService.findOrCreateTags(request.getTags());

        task.setDifficulty(difficulty);
        task.setTags(persistedTags);
        task.getTestCases().forEach(t -> t.setTask(task));
        return taskMapper.toResponse(taskRepository.save(task));
    }

    @Override
    public ResponseEntity<TaskResponse> getTasksWithRange(int start, int end) {
        
        return taskRepository.find;
    }
}

