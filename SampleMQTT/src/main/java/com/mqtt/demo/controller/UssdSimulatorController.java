package com.mqtt.demo.controller;

import java.util.*;
import org.springframework.web.bind.annotation.*;

import com.mqtt.demo.service.MockService;
import com.mqtt.demo.service.MqttProducer;

@RestController
@RequestMapping("/mock-ussd")
public class UssdSimulatorController {

    private final MqttProducer mqttProducer;
    private final MockService mockService;
    private Map<String, String> sessionMap = new HashMap<>();

    public UssdSimulatorController(MqttProducer mqttProducer, MockService mockService) {
        this.mqttProducer = mqttProducer;
        this.mockService = mockService;
    }

    @PostMapping
    public String handleUssd(@RequestBody Map<String, String> payload) {
        String sessionId = payload.get("sessionId");
        String text = payload.get("text");
        String[] inputs = text.split(",");

        String response;
        if (inputs.length == 1) {
            if ("1".equals(inputs[0])) {
                response = "Enter loan amount:";
                sessionMap.put(sessionId, text);  // Save session
            } else if ("2".equals(inputs[0])) {
                response = "Your loan balance is $500.";
            } else {
                response = "Invalid input.";
            }
        } else if (inputs.length == 2) {
            // Handle loan amount input
            String loanAmount = inputs[1];
            mqttProducer.publishMessage("/loan/application", loanAmount); // Publish to MQTT
            response = "Your loan application for $" + loanAmount + " has been submitted!";
        } else {
            response = "Invalid session.";
        }

        return response;
    }

}
