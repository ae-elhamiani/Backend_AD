package com.eainfo.demandService.service;

import com.eainfo.demandService.config.DemandParamsProperties;
import com.eainfo.demandService.config.DemandSecurityProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    private final DemandSecurityProperties demandSecurityProperties;
    private final DemandParamsProperties demandParamsProperties;


    public String generateToken(String subject) {

        String secretKey = demandSecurityProperties.getJwtSecret();

        System.out.println();
        System.out.println(Arrays.toString(demandSecurityProperties.getJwtSecret().getBytes()));
        System.out.println(demandSecurityProperties.getJwtSecret());

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + demandParamsProperties.getExpirationTimeMs()))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();
    }
}
