package din.kz.mind_forge_back.service;

import din.kz.mind_forge_back.model.entity.TestCase;

import java.util.Set;

public interface TestCaseService {
     Set<TestCase> getTestCasesWhithPublicTestCases(Long id);
}
