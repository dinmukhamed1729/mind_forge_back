package din.kz.mind_forge_back.controller;

import din.kz.mind_forge_back.service.CodeExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CodeExecutionController {

    private final CodeExecutionService codeExecutionService;

    @PostMapping("/execute")
    public String executeCode(@RequestParam("file") MultipartFile file,
                              @RequestParam("title") String title)
            throws IOException, InterruptedException {
        return codeExecutionService.executeCodeFromFile(file, title);
    }
}
