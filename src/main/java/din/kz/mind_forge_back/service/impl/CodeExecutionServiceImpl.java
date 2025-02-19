package din.kz.mind_forge_back.service.impl;

import din.kz.mind_forge_back.service.CodeExecutionService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CodeExecutionServiceImpl implements CodeExecutionService {

    private static final String UPLOAD_DIR = "./uploads/";

    private Path saveFile(MultipartFile file) throws IOException {
        var dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        var path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
        Files.write(path, file.getBytes());
        return path;
    }

    public String executeCodeFromFile(MultipartFile file) throws IOException, InterruptedException {
        var filePath = saveFile(file);
        var fileType = file.getContentType();
        var command  = getCommand(filePath, fileType);
        var process  = Runtime.getRuntime().exec(command);
        var exitCode = process.waitFor();
        var output   = new String(process.getInputStream().readAllBytes()).trim();
        var error    = new String(process.getErrorStream().readAllBytes()).trim();

        if (exitCode != 0) {
            return "Ошибка выполнения:\n" + error;
        }
        return output;
    }

    private String getCommand(Path filePath, String fileType) {
        var absolutePath = filePath.toAbsolutePath().toString();
        return switch (fileType) {
            case "text/x-java-source" -> "java " + absolutePath;
            case "text/x-python" -> "python " + absolutePath;
            case "text/x-c++src" -> "g++ " + absolutePath;
            default -> throw new IllegalStateException("Unexpected value: " + fileType);
        };
    }
}
