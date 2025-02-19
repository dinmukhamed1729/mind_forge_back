package din.kz.mind_forge_back.service.impl;

import din.kz.mind_forge_back.model.entity.TestCase;
import din.kz.mind_forge_back.repository.TestCaseRepository;
import din.kz.mind_forge_back.service.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TestCaseServiceImpl implements TestCaseService {

    private final TestCaseRepository testCaseRepository;

    @Override
    public Set<TestCase> getTestCasesWhithPublicTestCases(Long id) {
        return testCaseRepository.findAllByTaskIdAndIsPublicTrue(id).orElse(new HashSet<>());
    }
}
