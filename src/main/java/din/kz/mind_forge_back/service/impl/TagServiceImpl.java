package din.kz.mind_forge_back.service.impl;

import din.kz.mind_forge_back.model.entity.Tag;
import din.kz.mind_forge_back.repository.TagRepository;
import din.kz.mind_forge_back.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Override
    public Set<Tag> findOrCreateTags(Set<String> tagNames) {
        return tagNames.stream().map(tagName -> tagRepository.findByName(tagName).orElseGet(() -> {
            Tag tag = new Tag();
            tag.setName(tagName);
            return tagRepository.save(tag);
        })).collect(Collectors.toSet());
    }
}
