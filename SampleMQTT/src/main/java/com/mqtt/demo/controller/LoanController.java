package com.mqtt.demo.controller;


import org.springframework.web.bind.annotation.*;
import com.mqtt.demo.service.MockService;
import com.mqtt.demo.service.MqttProducer;

@RestController
@RequestMapping("/loan")
public class LoanController {
	
	private final MqttProducer mqttProducer;

    private final MockService mockService;

    public LoanController(MqttProducer mqttProducer, MockService mockService) {
        this.mqttProducer = mqttProducer;
        this.mockService = mockService;
    }


    @PostMapping("/application")
    public String applyForLoan(@RequestBody String loanDetails) {
        mqttProducer.publishMessage("/loan/application", loanDetails);
        return mockService.getMockResponse("/loan/application"); 
    }

    @GetMapping("/balance")
    public String getLoanBalance(@RequestParam(required = false) String loanId) {
        String topic = "/loan/balance";
        String message = loanId != null ? loanId : "DefaultLoanId";
        mqttProducer.publishMessage(topic, message);
        return mockService.getMockResponse(topic); 
    }

    @GetMapping("/status")
    public String checkLoanStatus(@RequestParam(required = false) String loanId) {
        String topic = "/loan/status";
        String message = loanId != null ? loanId : "DefaultLoanId";
        mqttProducer.publishMessage(topic, message);
        return mockService.getMockResponse(topic); 
    }
}
