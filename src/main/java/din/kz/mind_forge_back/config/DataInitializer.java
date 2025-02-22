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

import java.util.List;
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
        if (roleRepository.count() == 0) {
            List<Role> roles = Stream.of("USER", "ADMIN")
                    .map(Role::new)
                    .toList();
            roleRepository.saveAll(roles);
        }
    }

    private void initializeDifficulties() {
        if (difficultyRepository.count() == 0) {
            List<Difficulty> difficulties = Stream.of("Easy", "Medium", "Hard")
                    .map(Difficulty::new)
                    .toList();
            difficultyRepository.saveAll(difficulties);
        }
    }

    private void initializeTags() {
        if (tagRepository.count() == 0) {
            List<Tag> tags = Set.of("Math", "Greedy", "Dynamic Programming", "Graphs", "Strings").stream()
                    .map(Tag::new)
                    .toList();
            tagRepository.saveAll(tags);
        }
    }


}
