package com.zero.corebankingacl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOnboardRequestVO {
    private String customerName;
    private String coreBankingCustomerId;
    private String customerMobileNumber;
    private Date customerDob;
}
