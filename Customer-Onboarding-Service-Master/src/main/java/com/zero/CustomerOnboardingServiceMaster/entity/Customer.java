package com.zero.CustomerOnboardingServiceMaster.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document
public class Customer {
    @Id
    private String customerId;
    private String coreCustomerId;
    private String customerName;
    private String customerMobileNumber;
    private LocalDate customerDob;
    private String customerStatus;
}
