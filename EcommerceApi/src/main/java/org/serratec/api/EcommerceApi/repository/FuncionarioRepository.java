package org.serratec.api.EcommerceApi.repository;

import org.serratec.api.EcommerceApi.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{

}