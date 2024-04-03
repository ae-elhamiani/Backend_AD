package com.eainfo.otpService.repository;

import com.eainfo.otpService.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepository extends JpaRepository<Counter, Long> {
}
