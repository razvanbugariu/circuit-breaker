package rbu.circuitbreaker.resilience4j.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rbu.circuitbreaker.resilience4j.service.AppService;

@RestController
public class AppRestController {
    private AppService appService;

    @Autowired
    public AppRestController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/check")
    public ResponseEntity<String> check() {
        return appService.check();
    }
}
