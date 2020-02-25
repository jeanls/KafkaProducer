package com.jean.kafkaproducer.controller;

import com.jean.kafkaproducer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class UserController {
    final KafkaTemplate<String, User> kafkaTemplate;
    static final String TOPIC = "test";

    @Autowired
    public UserController(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping(value = "/publish", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> publish(@RequestBody final User message) {
        kafkaTemplate.send(TOPIC, message);
        return ResponseEntity.ok().build();
    }
}
