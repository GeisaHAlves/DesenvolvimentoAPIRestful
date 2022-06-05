package org.serratec.api.EcommerceApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.api.EcommerceApi.DTO.CompraDTO;
import org.serratec.api.EcommerceApi.DTO.ItemDTO;
import org.serratec.api.EcommerceApi.model.Compra;
import org.serratec.api.EcommerceApi.model.ComprasItem;
import org.serratec.api.EcommerceApi.model.Funcionario;
import org.serratec.api.EcommerceApi.model.Produto;
import org.serratec.api.EcommerceApi.repository.CompraRepository;
import org.serratec.api.EcommerceApi.repository.ComprasItemRepository;
import org.serratec.api.EcommerceApi.repository.FuncionarioRepository;
import org.serratec.api.EcommerceApi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Autowired
	CompraRepository compraRepository;
	
	@Autowired
	ComprasItemRepository comprasItemRepository;
	
	@Autowired
	ProdutoService produtoService;

	@Autowired
	ComprasItemService itemService;
	
	public Compra toModel(Compra compra, CompraDTO compraDTO) {

		Optional<Funcionario> funcionario = 
				funcionarioRepository.findById(compraDTO.getIdFuncionario());
		if (funcionario.isPresent()) {	
			compra.setFuncionario(funcionario.get());
		}
		
		Double valorTotal = 0.0;
		List<ComprasItem> itens = new ArrayList<>();
		
		for (ItemDTO itemDTO : compraDTO.getItens()) {
			ComprasItem item = new ComprasItem();
			Optional<Produto> produto = produtoRepository.findById(itemDTO.getIdProduto());
			if (produto.isPresent()) {
				valorTotal += (produto.get().getValorUnitario() * itemDTO.getQuantidade());
			}
			item.setCompra(compra);
			item = itemService.toModel(item, itemDTO);
			itens.add(item);
		}
		compra.setListaCompra(itens);
		compra.setValorTotal(valorTotal);

		return compra;
	}

	public CompraDTO toDTO(CompraDTO compraDTO, Compra compra) {

		compraDTO.setIdFuncionario(compra.getFuncionario().getIdFuncionario());
		
		List<ItemDTO> itens = new ArrayList<>();

		for (ComprasItem item : compra.getListaCompra()) {
			ItemDTO itemDTO = new ItemDTO();
			itemDTO = itemService.toDTO(itemDTO, item);
			itens.add(itemDTO);
		}
		
		compraDTO.setItens(itens);
		
		return compraDTO;
	}
	
	public String salvar(CompraDTO compraDTO) {
		Compra compra = new Compra();
		compra = toModel(compra, compraDTO);
		for (ComprasItem item : compra.getListaCompra()) {
			produtoService.atualizarEstoqueCompra(item.getProduto(), item.getQuantidade());
			comprasItemRepository.save(item);
		}
		
		compraRepository.save(compra);
		return "Compra do fornecedor finalizada com sucesso";
	}

}
