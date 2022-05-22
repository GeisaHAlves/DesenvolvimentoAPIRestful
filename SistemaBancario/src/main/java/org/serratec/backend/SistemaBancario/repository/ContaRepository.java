package org.serratec.backend.SistemaBancario.repository;

import java.util.Optional;

import org.serratec.backend.SistemaBancario.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{

	Optional<Conta> findByNumero(String numeroConta);

}
