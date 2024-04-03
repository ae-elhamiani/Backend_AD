package com.eainfo.clientService.repository;

import com.eainfo.clientService.model.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface PackRepository  extends JpaRepository<Pack, Long> {
    Pack findByCode(String code);
    List<Pack> findByClientProfileCode(String clientProfileCode);


}
