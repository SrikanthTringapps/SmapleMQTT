package com.mqtt.demo.service;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

@Service
public class MqttConsumer {
	
	private final MqttClient mqttClient;

    public MqttConsumer(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    @PostConstruct
    public void subscribeToTopics() {
        try {
            mqttClient.subscribe("/loan/response", (topic, message) -> {
                System.out.println("Received message: " + new String(message.getPayload()));
            });

            System.out.println("Subscribed to /loan/response topic.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
