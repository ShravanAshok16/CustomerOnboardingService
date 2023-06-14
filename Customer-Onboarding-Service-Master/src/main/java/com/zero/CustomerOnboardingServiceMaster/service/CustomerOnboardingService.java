package com.zero.CustomerOnboardingServiceMaster.service;

import com.zero.CustomerOnboardingServiceMaster.entity.Customer;
import com.zero.CustomerOnboardingServiceMaster.exception.CoreBankingException;
import com.zero.CustomerOnboardingServiceMaster.exception.CustomerDoesNotExistCoreBankingException;
import com.zero.CustomerOnboardingServiceMaster.exception.CustomerDoesNotExistException;
import com.zero.CustomerOnboardingServiceMaster.feign.CoreBankingACLClient;
import com.zero.CustomerOnboardingServiceMaster.model.CoreBankingResponseVO;
import com.zero.CustomerOnboardingServiceMaster.model.CustomerOnboardRequestVO;
import com.zero.CustomerOnboardingServiceMaster.model.CustomerOnboardResponseVO;
import com.zero.CustomerOnboardingServiceMaster.model.CustomerVO;
import com.zero.CustomerOnboardingServiceMaster.repository.CustomerOnboardingRepository;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.zero.CustomerOnboardingServiceMaster.constants.CustomerOnboardingConstants.ONBOARDING_STATUS;

@Service
@Log4j2
public class CustomerOnboardingService {

    private CustomerOnboardingRepository repository;
    private CoreBankingACLClient coreBankingACLClient;

    public CustomerOnboardingService(CustomerOnboardingRepository repository, CoreBankingACLClient coreBankingACLClient) {
        this.repository = repository;
        this.coreBankingACLClient = coreBankingACLClient;
    }

    public CustomerOnboardResponseVO register(CustomerOnboardRequestVO customerOnboardVO){
        String customerId = UUID.randomUUID().toString();
        Customer customer= repository.save(Customer
                .builder()
                .customerId(customerId)
                .customerMobileNumber(customerOnboardVO.getCustomerMobileNumber())
                .customerDob(customerOnboardVO.getCustomerDob())
                .build());

        return CustomerOnboardResponseVO
                .builder()
                .customerId(customerId)
                .onBoardingStatus(ONBOARDING_STATUS)
                .build();
    }

    public CustomerOnboardResponseVO validate(CustomerOnboardRequestVO customerOnboardVO) throws CoreBankingException, CustomerDoesNotExistCoreBankingException {
        CoreBankingResponseVO coreBankingResponseVO = null;
        try {
            coreBankingResponseVO = coreBankingACLClient.validateCustomerInfo(customerOnboardVO);
        }catch (FeignException e){
            throw new CoreBankingException();
        }
        if(coreBankingACLClient == null){
            throw new CustomerDoesNotExistCoreBankingException();
        }
        //validation logic goes here

        return CustomerOnboardResponseVO
                .builder()
                .coreBankingCustomerId(coreBankingResponseVO.getCoreBankingCustomerId())
                .build();
    }



    public CustomerOnboardResponseVO getCustomer(String customerId) throws CustomerDoesNotExistException {
        Customer customer = repository.findById(customerId)
                .map(cust -> {
                    return Customer
                            .builder()
                            .customerName(cust.getCustomerName())
                            .coreCustomerId(cust.getCoreCustomerId())
                            .customerMobileNumber(cust.getCustomerMobileNumber())
                            .customerId(cust.getCustomerId())
                            .customerDob(cust.getCustomerDob())
                            .build();
                })
                .orElseThrow(CustomerDoesNotExistException::new);
        return CustomerOnboardResponseVO
                .builder()
                .coreBankingCustomerId(customer.getCoreCustomerId())
                .customerId(customer.getCustomerId())
                .customerDob(customer.getCustomerDob())
                .customerMobileNumber(customer.getCustomerMobileNumber())
                .onBoardingStatus(customer.getCustomerStatus())
                .build();
    }
}
