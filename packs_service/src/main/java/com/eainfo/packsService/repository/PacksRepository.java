package com.eainfo.packsService.repository;

import com.eainfo.packsService.model.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface PacksRepository extends JpaRepository<Pack, Long> {
    List<Pack> findByClientProfileCode(String clientProfileCode);

}