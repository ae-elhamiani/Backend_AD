package com.eainfo.demandService.repository;

import com.eainfo.demandService.model.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandRepository extends JpaRepository<Demand, String> {
    Demand findByEmail(String email);
}
