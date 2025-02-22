package din.kz.mind_forge_back.exception;

import org.springframework.http.HttpStatus;

public class TaskNotFoundException extends ApplicationException {
    public TaskNotFoundException() {
        super("TaskNotFound", HttpStatus.NOT_FOUND);
    }
}
