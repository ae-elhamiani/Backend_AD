package com.eainfo.clientService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientRequest {

    private String uuidClient;
    private String email;
    private String keyPhone;
    private String numPhone;

    private String userInput;

    private String packCode;
    private String clientProfileCode;


    private Boolean sendCarte;


}
