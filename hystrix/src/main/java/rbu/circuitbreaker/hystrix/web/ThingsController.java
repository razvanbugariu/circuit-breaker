package rbu.circuitbreaker.hystrix.web;

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
    public String getSomething() throws InterruptedException {
        int secondsToWait = random.nextInt(7) + 4;
        System.out.println("Timeout is: " + secondsToWait + " seconds");
        Thread.sleep(secondsToWait * 1000);
        return "Thing " + secondsToWait;
    }
}
