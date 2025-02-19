package din.kz.mind_forge_back.model.response;

import lombok.Data;

import java.util.Set;

@Data
public class ShortTaskResponse {
    private String title, description;
    private String difficulty;
    private Set<String> tags;
}
