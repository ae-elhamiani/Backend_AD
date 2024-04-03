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
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="uuid")
    private String uuid;

    @Column(name="secret_key")
    private byte[] secretKey;

    @Column(name="email")
    private String email;

    @Column(name="email_status")
    private Boolean emailStatus = false;

    @Column(name="key_phone")
    private String keyPhone;

    @Column(name="num_phone")
    private String numPhone;

    @Column(name="phone_status")
    private Boolean phoneStatus = false;


    @ManyToOne
    @JoinColumn(name = "client_profile_id")
    private ClientProfile clientProfile;



    public Client(String email, String  uuid, byte[] secretKey, ClientProfile clientprofile) {
        this.email = email;
        this.uuid = uuid;
        this.secretKey = secretKey;
        this.clientProfile = clientprofile;
    }

}