package org.serratec.api.EcommerceApi.repository;

import org.serratec.api.EcommerceApi.model.VendasItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendasItemRepository extends JpaRepository<VendasItem, Integer>{

}