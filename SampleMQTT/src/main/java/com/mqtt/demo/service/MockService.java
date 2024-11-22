package com.mqtt.demo.service;

import org.springframework.stereotype.Service;

@Service
public class MockService {
	
	public String getMockResponse(String topic) {
        switch (topic) {
            case "/loan/application":
                return "{\"status\":\"Application Received\", \"loanId\":\"12345\"}";
            case "/loan/balance":
                return "{\"loanId\":\"12345\", \"balance\":500}";
            case "/loan/status":
                return "{\"loanId\":\"12345\", \"status\":\"Approved\"}";
            default:
                return "{\"error\":\"Invalid topic\"}";
        }
    }
}
