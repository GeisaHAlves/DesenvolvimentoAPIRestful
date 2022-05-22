package org.serratec.backend.SistemaBancario.service;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.SistemaBancario.enums.TipoOperacao;
import org.serratec.backend.SistemaBancario.exception.OperacaoInvalidaException;
import org.serratec.backend.SistemaBancario.exception.SaldoInsuficienteException;
import org.serratec.backend.SistemaBancario.exception.ValorInvalidoException;
import org.serratec.backend.SistemaBancario.model.Conta;
import org.serratec.backend.SistemaBancario.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

	@Autowired
	ContaRepository repo;
	
	public List<Conta> listarTodas(){
		return repo.findAll();
	}
	
	public Conta buscarPorNumero(String numeroConta){
		Optional<Conta> optionalConta = repo.findByNumero(numeroConta);

		Conta conta = null;

		if (optionalConta.isPresent()) {
			conta = optionalConta.get();
		}
		return conta;
	}
	
	public void adicionar(Conta novaConta) {
		repo.save(novaConta);
	}
	
	public Conta atualizar(String numeroConta, Conta conta) {
		Conta contaNoBanco = buscarPorNumero(numeroConta);

		if (conta.getNumero() != null) {
			contaNoBanco.setNumero(conta.getNumero());
		}
		if (conta.getTitular() != null) {
			contaNoBanco.setTitular(conta.getTitular());
		}
		
		repo.save(contaNoBanco);
		return contaNoBanco;
	}

	public void delete(String numeroConta) {
		Conta contaNoBanco = buscarPorNumero(numeroConta);
		repo.deleteById(contaNoBanco.getIdConta());
	}
	
	public void sacar(Double valor, Conta conta) throws ValorInvalidoException, SaldoInsuficienteException {
		if (valor < 0) {
			throw new ValorInvalidoException("O valor digitado para saque é inválido!");
		} else if(conta.getSaldo() < valor) {
			throw new SaldoInsuficienteException("Saldo insuficiente!");
		} else {
			Conta contaNoBanco = buscarPorNumero(conta.getNumero());
			contaNoBanco.sacar(valor);
			repo.save(contaNoBanco);
		}
	}
	
	public void depositar(Double valor, Conta conta) throws ValorInvalidoException {
		if (valor < 0) {
			throw new ValorInvalidoException("O valor digitado para saque é inválido!");
		} else {
			Conta contaNoBanco = buscarPorNumero(conta.getNumero());
			contaNoBanco.depositar(valor);
			repo.save(contaNoBanco);
		}
	}
	
	public void operacao(String operacao, String numero, Double valor) throws ValorInvalidoException, SaldoInsuficienteException, OperacaoInvalidaException {
		Conta contaNoBanco = buscarPorNumero(numero);
		if(operacao.toUpperCase().equals(TipoOperacao.SACAR.toString())) {
			sacar(valor, contaNoBanco);
		} else if(operacao.toUpperCase().equals(TipoOperacao.DEPOSITAR.toString())) {
			depositar(valor, contaNoBanco);
		} else {
			throw new OperacaoInvalidaException("Operação inválida!");
		}
	}
	
}
