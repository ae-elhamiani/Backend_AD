package com.eainfo.otpService.repository;

import com.eainfo.otpService.model.OtpGenerated;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpGeneratedRepository extends JpaRepository<OtpGenerated, Long> {
    OtpGenerated findTopBySecretKeyOrderByDateGenerationDesc(byte[] secretKey);
}
