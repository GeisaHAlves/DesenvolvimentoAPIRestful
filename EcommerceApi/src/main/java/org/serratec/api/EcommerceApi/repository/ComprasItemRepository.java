package org.serratec.api.EcommerceApi.repository;

import org.serratec.api.EcommerceApi.model.ComprasItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprasItemRepository extends JpaRepository<ComprasItem, Integer>{

}