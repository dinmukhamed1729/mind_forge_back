package din.kz.mind_forge_back.service.impl;

import din.kz.mind_forge_back.service.CodeExecutionService;
import din.kz.mind_forge_back.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CodeExecutionServiceImpl implements CodeExecutionService {

    private static final String UPLOAD_DIR = "./uploads/";
    private final TaskService taskService;

    private Path saveFile(MultipartFile file) throws IOException {
        var dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        var path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
        Files.write(path, file.getBytes());
        return path;
    }

    public String executeCodeFromFile(MultipartFile file, String title) throws IOException, InterruptedException {
        var filePath = saveFile(file);
        var fileType = file.getContentType();

        if (fileType == null) {
            throw new FileNotFoundException("File not found");
        }

        var testCases = taskService.getTaskTestCases(title);
        var command = getCommand(filePath, fileType);

        for (var testCase : testCases) {
            var processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true);
            var process = processBuilder.start();

            try (var outputStream = process.getOutputStream();
                 var writer = new PrintWriter(outputStream, true)) {
                writer.println(testCase.getInputData());
            }

            String output;
            try (var inputStream = process.getInputStream();
                 var reader = new BufferedReader(new InputStreamReader(inputStream))) {
                output = reader.lines().collect(Collectors.joining("\n"));
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                return "Ошибка выполнения:\n" + output;
            } else if (!output.equals(testCase.getExpectedOutput())) {
                return "false";
            }
        }
        return "true";
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
