package org.serratec.api.EcommerceApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.serratec.api.EcommerceApi.DTO.ProdutoDTO;
import org.serratec.api.EcommerceApi.DTO.RelatorioDTO;
import org.serratec.api.EcommerceApi.exception.EmailException;
import org.serratec.api.EcommerceApi.exception.EstoqueException;
import org.serratec.api.EcommerceApi.exception.PedidoException;
import org.serratec.api.EcommerceApi.exception.ProdutoException;
import org.serratec.api.EcommerceApi.model.Categoria;
import org.serratec.api.EcommerceApi.model.Pedido;
import org.serratec.api.EcommerceApi.model.Produto;
import org.serratec.api.EcommerceApi.model.VendasItem;
import org.serratec.api.EcommerceApi.repository.CategoriaRepository;
import org.serratec.api.EcommerceApi.repository.FuncionarioRepository;
import org.serratec.api.EcommerceApi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	FuncionarioRepository funcionarioRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	EmailService emailService;

	public Produto toModel(Produto produto, ProdutoDTO produtoDTO) {
		produto.setDataValidade(produtoDTO.getDataValidade());
		produto.setDataCadastro(produtoDTO.getDataCadastro());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setNome(produtoDTO.getNome());
		produto.setQtdEstoque(produtoDTO.getQntEstoque());
		produto.setValorUnitario(produtoDTO.getValor());

		if (produtoDTO.getIdCategoria() != null) {
			Optional<Categoria> categoria = categoriaRepository.findById(produtoDTO.getIdCategoria());
			if (categoria.isPresent()) {
				produto.setCategoria(categoriaRepository.findById(produtoDTO.getIdCategoria()).get());
			}
		}
		return produto;
	}

	public ProdutoDTO toDTO(ProdutoDTO produtoDTO, Produto produto) {
		produtoDTO.setIdProduto(produto.getIdProduto());
		produtoDTO.setDataValidade(produto.getDataValidade());
		produtoDTO.setDataCadastro(produto.getDataCadastro());
		produtoDTO.setDescricao(produto.getDescricao());
		produtoDTO.setNome(produto.getNome());
		produtoDTO.setQntEstoque(produto.getQtdEstoque());
		produtoDTO.setValor(produto.getValorUnitario());
		return produtoDTO;
	}

	public String salvar(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		produto = toModel(produto, produtoDTO);
		produtoRepository.save(produto);
		return "Novo produto cadastrado." + "\nID produto: " + produto.getIdProduto();
	}

	public ProdutoDTO buscarPorId(Integer idProduto) throws ProdutoException {
		Optional<Produto> prodOptional = produtoRepository.findById(idProduto);

		if (prodOptional.isPresent()) {
			Produto produto = prodOptional.get();
			ProdutoDTO produtoDTO = new ProdutoDTO();
			produtoDTO = toDTO(produtoDTO, produto);
			return produtoDTO;
		}
		throw new ProdutoException("Produto não encontrado.");

	}

	public void delete(Integer idProduto) {
		produtoRepository.deleteById(idProduto);
	}

	public String atualizar(Integer idProduto, ProdutoDTO produtoDTO) throws ProdutoException {
		Optional<Produto> prodOptional = produtoRepository.findById(idProduto);

		if (prodOptional.isPresent()) {
			Produto produto = prodOptional.get();
			if (produtoDTO.getValor() != null) {
				produto.setValorUnitario(produtoDTO.getValor());
			}
			if (produtoDTO.getDataValidade() != null) {
				produto.setDataValidade(produtoDTO.getDataValidade());
			}
			if (produtoDTO.getDescricao() != null) {
				produto.setDescricao(produtoDTO.getDescricao());
			}
			if (produtoDTO.getNome() != null) {
				produto.setNome(produtoDTO.getNome());
			}
			if (produtoDTO.getDataCadastro() != null) {
				produto.setDataCadastro(produtoDTO.getDataCadastro());
			}
			if (produtoDTO.getQntEstoque() != null) {
				produto.setQtdEstoque(produtoDTO.getQntEstoque());
			}
			produtoRepository.save(produto);
			return "Produto " + produto.getNome() + " foi atualizado.";
		}
		throw new ProdutoException("O produto não foi atualizado");
	}

	public List<ProdutoDTO> todosProdutos() {
		List<Produto> lisProdutos = produtoRepository.findAll();
		List<ProdutoDTO> produtoDTOs = new ArrayList<>();

		for (Produto produto : lisProdutos) {
			ProdutoDTO produtoDTO = new ProdutoDTO();
			produtoDTO = toDTO(produtoDTO, produto);
			produtoRepository.save(produto);
		}
		return produtoDTOs;
	}

	public String addEstoque(Integer idProduto, Integer quantidade) throws ProdutoException {
		Optional<Produto> prodOptional = produtoRepository.findById(idProduto);
		if (prodOptional.isPresent()) {
			Produto produto = prodOptional.get();
			atualizarEstoqueCompra(produto, quantidade);
		}
		throw new ProdutoException("O estoque não foi atualizado");
	}

	public void salvarListaProduto(List<ProdutoDTO> listaProdutoDTO) {
		for (ProdutoDTO produtoDTO : listaProdutoDTO) {
			Produto produto = new Produto();
			toModel(produto, produtoDTO);
			produtoRepository.save(produto);
		}
	}

	public void atualizarEstoqueVenda(Pedido pedido) throws EstoqueException, EmailException, MessagingException, PedidoException {
		for (VendasItem item : pedido.getItens()) {
			Optional<Produto> produtoOpt = produtoRepository.findById(item.getProduto().getIdProduto());
			if (produtoOpt.isPresent()) {
				Produto produto = produtoOpt.get();
				if(produto.getQtdEstoque() <= 0)
					throw new EstoqueException("Produto sem estoque.");
				produto.tirarEstoque(item.getQuantidade());
				if (produto.getQtdEstoque() <= 5) {
					emailService.emailProprietario(produto);
				}
					
				produtoRepository.save(produto);
			}
		}
	}

	public void atualizarEstoqueCompra(Produto produto, Integer quantidade) {
		produto.colocarEstoque(quantidade);
		produtoRepository.save(produto);
	}
	
	public List<RelatorioDTO> relatorio() {
		return produtoRepository.relatorio();
	}

}
