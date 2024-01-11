package com.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class EmployeeController {
    @Autowired
    private WebClient webClient;
    @GetMapping("/employee")
    public String getEmpWithAddress(){
        String employee="Sandeep Kumar Patel";
        Mono<String> address= (webClient.get().uri("/address").retrieve().bodyToMono(String.class)).timeout(Duration.ofSeconds(5));
        return "Employee: "+employee+" Address: "+address.block();

    }
}
