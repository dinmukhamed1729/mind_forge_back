package din.kz.mind_forge_back.model.response;

import java.util.Set;

public record ShortTaskResponse(String title, String description, String difficulty, Set<String> tags) {
}
