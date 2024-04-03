package com.eainfo.clientService.repository;

import com.eainfo.clientService.model.ClientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ClientProfileRepository extends JpaRepository<ClientProfile, Long> {
    ClientProfile findByCode(String code);

}
