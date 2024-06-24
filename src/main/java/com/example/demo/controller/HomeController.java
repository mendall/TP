package com.example.demo.controller;

import com.example.demo.kafka.KafkaProducerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HomeController {
    private KafkaProducerService kafkaProducerService;

    @GetMapping("/public")
    public String publicEndpoint() {
        kafkaProducerService.sendMessage("Message received by public endpoint and sent to kafka");
        return "Public endpoint response";
    }

    @GetMapping("/private")
    public String privateEndpoint() {
        kafkaProducerService.sendMessage("Message received by private endpoint and sent to kafka");
        return "Private endpoint response";
    }
}
