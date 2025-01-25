package din.kz.mind_forge_back.mapper;

import din.kz.mind_forge_back.model.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagMapper {
    @Mapping(target = "id", ignore = true)
    Tag toEntity(String name);
}
