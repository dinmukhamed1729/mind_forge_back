package din.kz.mind_forge_back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig {

    private final static List<String> HTTP_METHODS = Arrays.asList("GET", "POST", "PATCH", "DELETE", "PUT", "OPTIONS");
    private final static List<String> HEADERS = Arrays.asList("Content-Type", "Cookie", "Authorization", "Accept-Language", "User-Agent");

    @Bean
    public CorsConfigurationSource corsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setMaxAge(3600L); // Максимальное время кэширования конфигурации
        configuration.setAllowedOrigins(List.of("http://localhost:5173", "http://26.85.50.78:5173")); // Исправленный URL
        configuration.setAllowedMethods(HTTP_METHODS); // Разрешенные HTTP методы
        configuration.setAllowedHeaders(HEADERS); // Разрешенные заголовки
        configuration.setAllowCredentials(true); // Разрешаем отправку cookies

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Применяем ко всем путям

        return source;
    }
}

