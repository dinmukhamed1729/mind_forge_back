package din.kz.mind_forge_back.config;

import din.kz.mind_forge_back.model.entity.Difficulty;
import din.kz.mind_forge_back.model.entity.Tag;
import din.kz.mind_forge_back.repository.DifficultyRepository;
import din.kz.mind_forge_back.repository.RoleRepository;
import din.kz.mind_forge_back.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import din.kz.mind_forge_back.model.entity.Role;

import java.util.Set;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

    private final DifficultyRepository difficultyRepository;
    private final TagRepository tagRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void run(String... args) {
        initializeDifficulties();
        initializeTags();
        initializeRole();
    }

    private void initializeRole() {
        Stream.of("USER", "ADMIN")
                .filter(role -> !roleRepository.existsByName(role))
                .map(Role::new).forEach(roleRepository::save);
    }

    private void initializeDifficulties() {
        Stream.of("Easy", "Medium", "Hard")
                .filter(level -> difficultyRepository.findByLevel(level).isEmpty())
                .map(Difficulty::new).forEach(difficultyRepository::save);
    }

    private void initializeTags() {
        Set.of("Math", "Greedy", "Dynamic Programming", "Graphs", "Strings").stream()
                .filter(tag -> tagRepository.findByName(tag).isEmpty())
                .map(Tag::new).forEach(tagRepository::save);
    }
}
