package din.kz.mind_forge_back.mapper;

import din.kz.mind_forge_back.model.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TagMapper {
    @Mapping(target = "id", ignore = true)
    Tag toEntity(String name);

    @Named("toTagNames")
    default Set<String> toTagNames(Set<Tag> tags) {
        if (tags == null) return new HashSet<>();
        return tags.stream()
                .map(Tag::getName)  // Получаем name из Tag
                .collect(Collectors.toSet());
    }
}
