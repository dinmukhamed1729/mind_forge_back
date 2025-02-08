package din.kz.mind_forge_back.controller;


import din.kz.mind_forge_back.model.request.CreateTaskRequest;
import din.kz.mind_forge_back.model.response.TaskResponse;
import din.kz.mind_forge_back.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1")
public class TaskController {

    private final TaskService taskService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/task")
    public TaskResponse createTask(@RequestBody CreateTaskRequest request) {
        System.out.println(request);
        return taskService.createTask(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/task")
    public ResponseEntity<TaskResponse> getTask(@RequestParam int start, @RequestParam int end) {
        return taskService.getTasksWithRange(start,end);
    }

}
