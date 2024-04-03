package com.eainfo.clientService.repository;

import com.eainfo.clientService.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CompteRepository extends JpaRepository<Compte, Long> {

}
