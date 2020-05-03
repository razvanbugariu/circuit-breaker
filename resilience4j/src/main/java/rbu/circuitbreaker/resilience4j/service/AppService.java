package rbu.circuitbreaker.resilience4j.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class AppService {

    private final CircuitBreaker circuitBreaker;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public AppService(CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }

    public ResponseEntity<String> check() {
        Supplier<ResponseEntity<String>> decorated = CircuitBreaker.decorateSupplier(circuitBreaker, () -> restTemplate.getForEntity("http://localhost:8080/thing", String.class));
        return Try.ofSupplier(decorated).recover(throwable -> ResponseEntity.of(Optional.of("Something went wrong"))).get();
    }

}
