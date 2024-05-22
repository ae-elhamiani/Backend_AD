package com.eainfo.clientService.model;

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
@Table(name = "pack")
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

//    @Temporal(TemporalType.DATE)
//    @Column(name = "creation_date")
//    private Date creationDate;
//
//    @Temporal(TemporalType.DATE)
//    @Column(name = "expiration_date")
//    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "client_profile_id")
    private ClientProfile clientProfile;

//    public Pack() {
//
//    }


}