package din.kz.mind_forge_back.service.impl;

import din.kz.mind_forge_back.mapper.TaskMapper;
import din.kz.mind_forge_back.model.entity.Difficulty;
import din.kz.mind_forge_back.model.entity.Task;
import din.kz.mind_forge_back.model.request.CreateTaskRequest;
import din.kz.mind_forge_back.repository.DifficultyRepository;
import din.kz.mind_forge_back.repository.TaskRepository;
import din.kz.mind_forge_back.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final DifficultyRepository difficultyRepository;
    private final TaskMapper taskMapper;

    @Override
    public Task createTask(CreateTaskRequest request) {
        Task task = taskMapper.taskRequestToTask(request);

        Difficulty difficulty = difficultyRepository.findByLevel(request.getDifficulty())
                .orElseThrow(() -> new IllegalArgumentException("Difficulty level not found: " + request.getDifficulty()));
        task.setDifficulty(difficulty);

        return taskRepository.save(task);
    }
}

