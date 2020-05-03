package rbu.circuitbreaker.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class AppService {

    private RestTemplate restTemplate = new RestTemplate();

    @HystrixCommand(
        commandKey = "checkCommand",
        fallbackMethod = "hystryxFallback"
    )
    public ResponseEntity<String> check() {
        return restTemplate.getForEntity("http://localhost:8090/thing", String.class);
    }

    private ResponseEntity<String> hystryxFallback() {
        return ResponseEntity.of(Optional.of("Circuit opened, this is fallback method."));
    }
}
