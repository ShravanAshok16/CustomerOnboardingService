package com.zero.CustomerOnboardingServiceMaster.controller;

import com.zero.CustomerOnboardingServiceMaster.exception.CoreBankingException;
import com.zero.CustomerOnboardingServiceMaster.exception.CustomerDoesNotExistCoreBankingException;
import com.zero.CustomerOnboardingServiceMaster.exception.CustomerDoesNotExistException;
import com.zero.CustomerOnboardingServiceMaster.model.CustomerOnboardRequestVO;
import com.zero.CustomerOnboardingServiceMaster.model.CustomerOnboardResponseVO;
import com.zero.CustomerOnboardingServiceMaster.model.CustomerVO;
import com.zero.CustomerOnboardingServiceMaster.service.CustomerOnboardingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerOnboardingController {
    private CustomerOnboardingService service;

    public CustomerOnboardingController(CustomerOnboardingService service) {
        this.service = service;
    }

    @PostMapping("/customer/v1/onboard")
    public ResponseEntity<CustomerOnboardResponseVO> onboardCustomer(@RequestBody CustomerOnboardRequestVO customerOnboardVO){
        return ResponseEntity.ok(service.register(customerOnboardVO));
    }

    @PostMapping("/customer/v1/validate")
    public ResponseEntity<CustomerOnboardResponseVO> validateCoreCustomer(@RequestBody CustomerOnboardRequestVO customerOnboardVO) throws CoreBankingException, CustomerDoesNotExistCoreBankingException {
        return ResponseEntity.ok(service.validate(customerOnboardVO));
    }

    @GetMapping("/v1/customer/{customerId}")
    public ResponseEntity<CustomerOnboardResponseVO> getCustomer(@PathVariable String customerId) throws CustomerDoesNotExistException {
        return ResponseEntity.ok(service.getCustomer(customerId));

    }
}
