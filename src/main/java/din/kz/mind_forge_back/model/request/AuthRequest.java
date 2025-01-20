package din.kz.mind_forge_back.model.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
