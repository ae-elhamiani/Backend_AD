package com.eainfo.otpService.model;

import com.eainfo.openfeignService.otp.enums.OtpState;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@Component
@AllArgsConstructor
@Data
@Table(name = "otpGenerated")
public class OtpGenerated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty
    @Column(name = "secretkey")
    private byte[] secretKey;

    @Column(name = "nb_attempt")
    private Integer nb_attempt;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private OtpState state;

    @Column(name = "nb_generation")
    private Integer nb_generation;

    @Column(name = "date_generation")
    private Date date_generation;

    @Column(name = "counter")
    private Integer counter = 0;


    public OtpGenerated(byte[] secretKey, Integer nb_attempt, OtpState state, Integer nb_generation, Date dateGeneration) {
        this.secretKey = secretKey;
        this.nb_attempt = nb_attempt;
        this.state = state;
        this.nb_generation =nb_generation;
        this.date_generation = dateGeneration;

    }

    public void incrementCounter() {
        this.counter++;
    }

    public void incrementNb_generation() {
        this.nb_generation++;
    }
    public void incrementNb_attempt() {
        this.nb_attempt++;
    }



}
