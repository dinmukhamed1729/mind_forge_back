package din.kz.mind_forge_back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

public class WebConfig {

    private final static List<String> HTTP_METHODS = Arrays.asList("GET", "POST", "PATCH", "DELETE", "PUT", "OPTIONS");
    private final static List<String> HEADERS = Arrays.asList("Content-Type", "Cookie", "Authorization", "Accept-Language", "User-Agent");

    @Bean
    public CorsConfigurationSource corsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setMaxAge(3600L);
        configuration.setAllowedOrigins(List.of("http://localhost:5173", "http://26.85.50.78:5173"));
        configuration.setAllowedMethods(HTTP_METHODS);
        configuration.setAllowedHeaders(HEADERS);
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}

