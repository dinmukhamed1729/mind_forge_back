package din.kz.mind_forge_back.mapper;


import din.kz.mind_forge_back.model.dto.TestCaseDTO;
import din.kz.mind_forge_back.model.entity.TestCase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestCaseMapper {
    TestCase toTestCase(TestCaseDTO testCaseDTO);
}