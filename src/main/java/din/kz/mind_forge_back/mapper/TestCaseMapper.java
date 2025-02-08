package din.kz.mind_forge_back.mapper;


import din.kz.mind_forge_back.model.dto.TestCaseDTO;
import din.kz.mind_forge_back.model.entity.TestCase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TestCaseMapper {

    @Mapping(target = "id", ignore = true)
    TestCase toTestCase(TestCaseDTO testCaseDTO);
}