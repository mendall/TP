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
        kafkaProducerService.sendMessage("Message received by public endpoint");
        return "Public endpoint";
    }

    @GetMapping("/private")
    public String privateEndpoint() {
        kafkaProducerService.sendMessage("Message received by private endpoint");
        return "Private endpoint";
    }
}
