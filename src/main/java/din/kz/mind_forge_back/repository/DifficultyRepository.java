package din.kz.mind_forge_back.repository;

import din.kz.mind_forge_back.model.entity.Difficulty;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DifficultyRepository extends JpaRepository<Difficulty, Integer> {
    Optional<Difficulty> findByLevel(String level);
}
