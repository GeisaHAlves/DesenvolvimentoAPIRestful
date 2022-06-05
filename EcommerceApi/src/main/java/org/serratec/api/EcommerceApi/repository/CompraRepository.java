package org.serratec.api.EcommerceApi.repository;

import org.serratec.api.EcommerceApi.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer>{

}