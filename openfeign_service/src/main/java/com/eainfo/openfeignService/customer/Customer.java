package com.eainfo.openfeignService.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private String idClient;
    private byte[] secretKey;
    private String profileClientCode;
    private String email;
    private String keyPhone;
    private String numPhone;


}
