package din.kz.mind_forge_back.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1")
public class CodeExecutionController {

    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping("/execute")
    public String executeCode(@RequestParam("file") MultipartFile file) throws IOException {
        Path filePath = saveFile(file);

        String result = executeCodeFromFile(filePath);

        Files.delete(filePath);

        return result;
    }

    private Path saveFile(MultipartFile file) throws IOException {
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
        Files.write(path, file.getBytes());
        return path;
    }

    private String executeCodeFromFile(Path filePath) {
        // Для простоты используем команду для выполнения кода через Java. Это можно изменить в зависимости от языка.

        String command = "java -cp " + filePath.getParent() + " " + filePath.getFileName().toString().replace(".java", "");
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            // Читаем вывод из процесса
            String output = new String(process.getInputStream().readAllBytes());
            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка выполнения кода: " + e.getMessage();
        }
    }
}
