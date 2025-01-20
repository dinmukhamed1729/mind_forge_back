package din.kz.mind_forge_back.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegistrationRequest {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}
