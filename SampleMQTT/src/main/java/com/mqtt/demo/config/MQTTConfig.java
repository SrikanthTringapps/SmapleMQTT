package com.mqtt.demo.config;

import org.springframework.context.annotation.Configuration;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;

@Configuration
public class MQTTConfig {
	
	@Bean
    public MqttClient mqttClient() throws Exception {
        String broker = "tcp://localhost:1883"; // Mock MQTT broker URL
        String clientId = "MockMqttBackend";
        
        MqttClient client = new MqttClient(broker, clientId, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        client.connect(options);

        System.out.println("Connected to MQTT Broker: " + broker);
        return client;
    }

}
