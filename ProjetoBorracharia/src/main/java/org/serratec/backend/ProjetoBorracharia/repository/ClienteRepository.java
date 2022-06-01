package org.serratec.backend.ProjetoBorracharia.repository;

import org.serratec.backend.ProjetoBorracharia.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer>{
}
