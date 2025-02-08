package din.kz.mind_forge_back.service.impl;

import din.kz.mind_forge_back.model.entity.Difficulty;
import din.kz.mind_forge_back.repository.DifficultyRepository;
import din.kz.mind_forge_back.service.DifficultlyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DifficultlyServiceImpl implements DifficultlyService {
    private final DifficultyRepository difficultyRepository;

    @Override
    public Difficulty findDifficultly(String difficulty) {
        return difficultyRepository.findByLevel(difficulty)
                .orElseThrow(() -> new IllegalArgumentException("Difficulty level not found: " + difficulty));
    }
}
