package din.kz.mind_forge_back.service;

import din.kz.mind_forge_back.model.dto.TaskDTO;
import din.kz.mind_forge_back.model.entity.Task;
import din.kz.mind_forge_back.model.request.CreateTaskRequest;
import din.kz.mind_forge_back.model.response.ShortTaskResponse;
import din.kz.mind_forge_back.model.response.TaskResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TaskService {
   ShortTaskResponse createTask(CreateTaskRequest request);

     Page<ShortTaskResponse> getTasksWithRange(Pageable pageable);

     @Transactional
     Optional<Task> getByTitleWithPublicTestCases(String title);
}
