package com.eainfo.demandService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemandRequest {
    private String id;
    private String email;
    private String keyPhone;
    private String numPhone;
    private String descriptionDemandOff;
    private Boolean stateNumTiers;
    private String statusVisio;

    private String userInput;

    private String packId;
    private String ProfileClientCode;

    private Boolean sendCarte;
}
