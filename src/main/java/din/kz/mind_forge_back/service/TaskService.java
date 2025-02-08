package din.kz.mind_forge_back.service;

import din.kz.mind_forge_back.model.entity.Task;
import din.kz.mind_forge_back.model.request.CreateTaskRequest;
import din.kz.mind_forge_back.model.response.TaskResponse;
import org.springframework.http.ResponseEntity;

public interface TaskService {
   TaskResponse createTask(CreateTaskRequest request);

    ResponseEntity<TaskResponse> getTasksWithRange(int start, int end);
}
