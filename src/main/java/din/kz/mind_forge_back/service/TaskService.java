package din.kz.mind_forge_back.service;

import din.kz.mind_forge_back.model.entity.Task;
import din.kz.mind_forge_back.model.request.CreateTaskRequest;

public interface TaskService {
   Task createTask(CreateTaskRequest request);
}
