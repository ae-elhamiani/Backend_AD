package com.eainfo.clientService.model;

import com.eainfo.clientService.enums.ClientStatus;
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
    @Column(name="id")
    private String id;

    @Column(name="secret_key")
    private byte[] secretKey;

    @Enumerated(EnumType.STRING)
    @Column(name="client_status")
    private ClientStatus clientStatus;

    @Column(name="profile_client_code")
    private String profileClientCode;

    @Column(name="email")
    private String email;

    @Column(name="key_phone")
    private String keyPhone;

    @Column(name="num_phone")
    private String numPhone;

}