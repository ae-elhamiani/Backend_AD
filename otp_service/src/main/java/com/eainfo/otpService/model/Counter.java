package com.eainfo.otpService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Entity
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "counter")
public class Counter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer counter = 0;

    public void incrementCounter() {
        this.counter++;
    }
}
