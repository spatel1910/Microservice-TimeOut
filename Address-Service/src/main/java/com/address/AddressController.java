package com.address;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    @GetMapping("/address")
    public String getAddress() throws InterruptedException {
        Thread.sleep(2000);
        return "86/k Village Tikuri 32 Rewa MP";
    }
}


