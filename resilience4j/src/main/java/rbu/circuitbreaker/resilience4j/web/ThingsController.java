package rbu.circuitbreaker.resilience4j.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class ThingsController {

    private Random random;

    @Autowired
    public ThingsController(Random random) {
        this.random = random;
    }
    @GetMapping("/thing")
    public String getThing() throws InterruptedException {
        int secondsToWait = random.nextInt(5) + 1;
        System.out.println("Seconds to wait: " + secondsToWait);
        Thread.sleep(secondsToWait * 1000);
        return "Thing " + secondsToWait;
    }
}
