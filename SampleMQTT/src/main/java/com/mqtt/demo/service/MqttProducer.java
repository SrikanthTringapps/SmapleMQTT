package com.mqtt.demo.service;

import org.springframework.stereotype.Service;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

@Service
public class MqttProducer {
	
	private final MqttClient mqttClient;

    public MqttProducer(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    public void publishMessage(String topic, String message) {
        try {
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(1);
            mqttClient.publish(topic, mqttMessage);
            System.out.println("Message published to topic: " + topic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
