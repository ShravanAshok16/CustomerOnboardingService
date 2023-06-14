package com.zero.CustomerOnboardingServiceMaster.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVO {
    private String customerId;
    private String coreBankingCustomerId;
    private String customerMobileNumber;
    private LocalDate customerDateOfBirth;
}
