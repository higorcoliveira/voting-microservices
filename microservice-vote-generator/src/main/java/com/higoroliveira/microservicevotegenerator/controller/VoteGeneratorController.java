package com.higoroliveira.microservicevotegenerator.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

@RestController
public class VoteGeneratorController {
    Random random = new Random();

    @GetMapping(path = "/random-numbers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Integer> getRandomNumbers() {
        return Flux.interval(Duration.ofSeconds(3))
                .map(e -> random.nextInt(100));
    }
}
