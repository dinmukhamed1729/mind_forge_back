package din.kz.mind_forge_back.controller;


import din.kz.mind_forge_back.model.entity.Task;
import din.kz.mind_forge_back.model.request.CreateTaskRequest;
import din.kz.mind_forge_back.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/task")
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskRequest request) {
        try {
            Task task = taskService.createTask(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(task);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
