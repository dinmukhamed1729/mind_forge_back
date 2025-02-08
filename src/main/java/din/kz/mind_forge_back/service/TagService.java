package din.kz.mind_forge_back.service;

import din.kz.mind_forge_back.model.entity.Tag;

import java.util.Set;

public interface TagService {
    Set<Tag> findOrCreateTags(Set<String> tags);
}
