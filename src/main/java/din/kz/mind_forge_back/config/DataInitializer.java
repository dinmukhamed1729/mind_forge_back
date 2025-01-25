package din.kz.mind_forge_back.config;

import din.kz.mind_forge_back.model.entity.Difficulty;
import din.kz.mind_forge_back.model.entity.Tag;
import din.kz.mind_forge_back.repository.DifficultyRepository;
import din.kz.mind_forge_back.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

    private final DifficultyRepository difficultyRepository;
    private final TagRepository tagRepository;


    @Override
    @Transactional
    public void run(String... args) {
        initializeDifficulties();
        initializeTags();
    }

    private void initializeDifficulties() {
        List<String> defaultDifficulties = List.of("Easy", "Medium", "Hard");

        for (String level : defaultDifficulties) {
            if (difficultyRepository.findByLevel(level).isEmpty()) {
                Difficulty difficulty = new Difficulty();
                difficulty.setLevel(level);
                difficultyRepository.save(difficulty);
            }
        }
    }

    private void initializeTags() {
        Set<String> defaultTags = Set.of("Math", "Greedy", "Dynamic Programming", "Graphs", "Strings");

        for (String tagName : defaultTags) {
            if (tagRepository.findByName(tagName).isEmpty()) {
                Tag tag = new Tag();
                tag.setName(tagName);
                tagRepository.save(tag);
            }
        }
    }
}
