package com.eainfo.demandService.model;

import com.eainfo.demandService.enums.StatusVisio;
import com.eainfo.demandService.enums.StepWeb;
import com.eainfo.openfeignService.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "demand")
public class Demand {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="id_client")
    private String idClient;

    @Column(name="email")
    private String email;

    @Transient
    private Customer client;

    @Column(name="id_compte")
    private String idCompte;

    @Column(name="step_web")
    @Enumerated(EnumType.STRING)
    private StepWeb stepWeb;

    @Column(name="description_demand_off")
    private String descriptionDemandOff;

    @Column(name="state_num_tier")
    private Boolean stateNumTiers = false;

    @Column(name="status_visio")
    @Enumerated(EnumType.STRING)
    private StatusVisio statusVisio;


    public void advanceToNextStep() {
            this.stepWeb = this.stepWeb.getNext();
    }
}
