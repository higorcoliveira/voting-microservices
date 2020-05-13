package com.higoroliveira.red.controller;

import com.higoroliveira.red.dto.VoteDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RedServiceController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${random.value}")
    private String district;

    @Value("${service.voteUrl}")
    private String voteUrl;

    @GetMapping("/instances")
    List<?> getInstance() {
        return this.discoveryClient.getServices()
                .stream()
                .map(serviceId -> this.discoveryClient.getInstances(serviceId))
                .collect(Collectors.toList());
    }

    @CircuitBreaker(fallbackMethod = "emptyVote", name = "default")
    @GetMapping(path = "/votes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<VoteDTO> getVotes() {
        return this.webClientBuilder.build()
                .get()
                .uri(this.voteUrl)
                .retrieve()
                .bodyToFlux(Integer.class)
                .map(count -> VoteDTO.builder()
                        .count(count)
                        .district(this.district)
                        .unavailableMessage("")
                        .build());
    }

    @GetMapping(path = "/empty-vote", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<VoteDTO> emptyVote(Exception ex) {
        return Flux.just(VoteDTO.builder()
                .count(0)
                .district(district)
                .unavailableMessage("No data available")
                .build());
    }
}
