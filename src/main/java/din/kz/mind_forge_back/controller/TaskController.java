package din.kz.mind_forge_back.controller;


import din.kz.mind_forge_back.mapper.TaskMapper;
import din.kz.mind_forge_back.model.request.CreateTaskRequest;
import din.kz.mind_forge_back.model.response.ShortTaskResponse;
import din.kz.mind_forge_back.model.response.TaskResponse;
import din.kz.mind_forge_back.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/task")
    public ShortTaskResponse createTask(@RequestBody CreateTaskRequest request) {
        return taskService.createTask(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/task")
    public Page<ShortTaskResponse> getTask(Pageable pageable) {
        return taskService.getTasksWithRange(pageable);
    }

    @GetMapping("/task/{title}")
    public ResponseEntity<TaskResponse> getTaskByTitle(@PathVariable String title) {
        System.out.println("title: " + title);
        return taskService.getByTitleWithPublicTestCases(title)
                .map(taskMapper::toTaskResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
