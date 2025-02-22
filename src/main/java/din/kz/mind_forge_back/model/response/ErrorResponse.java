package din.kz.mind_forge_back.model.response;

import java.time.Instant;

public record ErrorResponse(String message, int status, String timestamp) {
    public ErrorResponse(String message, int status) {
        this(message, status, Instant.now().toString());
    }
}
