package din.kz.mind_forge_back.model.dto;

public record TestCaseDTO(
        String inputData,
        String expectedOutput,
        boolean isPublic) {
}
