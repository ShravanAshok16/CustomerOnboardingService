package com.zero.CustomerOnboardingServiceMaster.controller.advice;

import com.zero.CustomerOnboardingServiceMaster.exception.CoreBankingException;
import com.zero.CustomerOnboardingServiceMaster.exception.CustomerDoesNotExistCoreBankingException;
import com.zero.CustomerOnboardingServiceMaster.exception.CustomerDoesNotExistException;
import com.zero.CustomerOnboardingServiceMaster.model.ErrorResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.zero.CustomerOnboardingServiceMaster.constants.CustomerOnboardingConstants.*;

@ControllerAdvice

public class CustomerOnboardingControllerAdvice {
    @ExceptionHandler(CoreBankingException.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public @ResponseBody ErrorResponseVO handleCoreBankingException(){
        return ErrorResponseVO.builder().errorCode(CORE_BANKING_ERROR_CODE).build();
    }

    @ExceptionHandler(CustomerDoesNotExistCoreBankingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponseVO handleCustomerDoesNotExistCoreException(){
        return ErrorResponseVO.builder().errorCode(CUSTOMER_NOT_FOUND_CORE_BANKING_ERROR_CODE).build();
    }

    @ExceptionHandler(CustomerDoesNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponseVO handleCustomerDoesNotExistException(){
        return ErrorResponseVO.builder().errorCode(CUSTOMER_NOT_FOUND_ERROR_CODE).build();
    }

}
