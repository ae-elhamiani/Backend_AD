package com.eainfo.packsService.model;

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
@Table(name = "packs_service")
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "client_profile_id")
    private ClientProfile clientProfile;




}