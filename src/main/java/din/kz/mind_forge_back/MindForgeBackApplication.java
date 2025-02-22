package din.kz.mind_forge_back;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication

public class MindForgeBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(MindForgeBackApplication.class, args);
    }
}
