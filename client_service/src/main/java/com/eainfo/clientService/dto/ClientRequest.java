package com.eainfo.clientService.dto;

import com.eainfo.clientService.enums.ClientStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientRequest {

    private String idClient;
    private String email;
    private String keyPhone;
    private String numPhone;
    private ClientStatus clientStatus;

    private String userInput;

    private String packId;
    private String clientProfileCode;

    private Boolean sendCarte;


}
