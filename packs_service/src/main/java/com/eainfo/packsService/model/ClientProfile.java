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
@Table(name = "client_profile")
public class ClientProfile {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "profile_name")
        private String name;

        @Column(name = "profile_code")
        private String code;

        @Lob
        @Column(name = "profile_image")
        private byte[] image;


}
