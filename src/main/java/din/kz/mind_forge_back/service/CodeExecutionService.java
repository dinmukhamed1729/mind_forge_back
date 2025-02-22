package din.kz.mind_forge_back.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CodeExecutionService {
    String executeCodeFromFile(MultipartFile file,String title) throws IOException, InterruptedException;
}
