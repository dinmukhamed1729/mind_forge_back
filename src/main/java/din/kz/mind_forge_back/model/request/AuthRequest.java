package din.kz.mind_forge_back.model.request;

import lombok.Data;

public record AuthRequest(String username, String password) {
}
