package com.eainfo.packsService.repository;

import com.eainfo.packsService.model.ClientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ClientProfileRepository extends JpaRepository<ClientProfile, Long> {

}
