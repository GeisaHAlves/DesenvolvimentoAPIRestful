package org.serratec.api.EcommerceApi.service;

import java.util.Optional;

import org.serratec.api.EcommerceApi.DTO.ItemDTO;
import org.serratec.api.EcommerceApi.model.ComprasItem;
import org.serratec.api.EcommerceApi.model.Produto;
import org.serratec.api.EcommerceApi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComprasItemService {

	@Autowired
	ProdutoRepository produtoRepository;

	public ComprasItem toModel(ComprasItem item, ItemDTO itemDTO) {
		item.setQuantidade(itemDTO.getQuantidade());
		Optional<Produto> produto = produtoRepository.findById(itemDTO.getIdProduto());
		if (produto.isPresent()) {
			item.setProduto(produto.get());
			item.setPreco(produto.get().getValorUnitario() * item.getQuantidade());
		}
		return item;
	}

	public ItemDTO toDTO(ItemDTO itemDTO, ComprasItem item) {
		itemDTO.setIdProduto(item.getProduto().getIdProduto());
		itemDTO.setQuantidade(item.getQuantidade());
		return itemDTO;
	}

}
