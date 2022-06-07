package org.serratec.api.EcommerceApi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;

import org.serratec.api.EcommerceApi.DTO.ItemDTO;
import org.serratec.api.EcommerceApi.DTO.PedidoDTO;
import org.serratec.api.EcommerceApi.exception.EmailException;
import org.serratec.api.EcommerceApi.exception.EstoqueException;
import org.serratec.api.EcommerceApi.exception.PedidoException;
import org.serratec.api.EcommerceApi.model.Cliente;
import org.serratec.api.EcommerceApi.model.Pedido;
import org.serratec.api.EcommerceApi.model.VendasItem;
import org.serratec.api.EcommerceApi.repository.ClienteRepository;
import org.serratec.api.EcommerceApi.repository.PedidoRepository;
import org.serratec.api.EcommerceApi.repository.ProdutoRepository;
import org.serratec.api.EcommerceApi.repository.VendasItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	VendasItemRepository vendasItemRepository;
	@Autowired
	VendasItemService vendasItemService;
	@Autowired
	ProdutoService produtoService;
	@Autowired
	EmailService emailService;

	public Pedido toModel(Pedido pedido, PedidoDTO pedidoDTO) {
		Random rng = new Random();
		pedido.setNotaFiscal(rng.nextInt(99999));
		pedido.setTipoPedido("Compra");
		pedido.setDataPedido(LocalDate.now());

		if (pedidoDTO.getIdCliente() != null) {
			Optional<Cliente> cliente = clienteRepository.findById(pedidoDTO.getIdCliente());
			if (cliente.isPresent()) {
				pedido.setCliente(cliente.get());
			}
		}

		List<VendasItem> itens = new ArrayList<>();
		Double valorTotal = 0.0;

		for (ItemDTO itemDTO : pedidoDTO.getItens()) {
			VendasItem item = new VendasItem();
			item.setPedido(pedido);
			item = vendasItemService.toModel(item, itemDTO);
			itens.add(item);
			valorTotal += item.getPreco();
		}

		pedido.setItens(itens);
		pedido.setValorTotal(valorTotal);

		return pedido;
	}

	public PedidoDTO toDTO(PedidoDTO pedidoDTO, Pedido pedido) {
		pedidoDTO.setIdCliente(pedido.getCliente().getIdCliente());
		
		List<ItemDTO> itens = new ArrayList<>();
		Double valorTotal = 0.0;

		for (VendasItem item : pedido.getItens()) {
			ItemDTO itemDTO = new ItemDTO();
			itemDTO = vendasItemService.toDTO(itemDTO, item);
			itens.add(itemDTO);
			valorTotal += item.getPreco();
		}
		
		pedidoDTO.setItens(itens);
		pedido.setValorTotal(valorTotal);
		
		return pedidoDTO;
	}
	
	void salvarListaVendas(List<VendasItem> itens) {
		for (VendasItem item : itens) {
			vendasItemRepository.save(item);
		}
	}

	public String salvar(PedidoDTO pedidoDTO) throws EmailException, MessagingException, PedidoException, EstoqueException {
		Pedido pedido = new Pedido();
		pedido = toModel(pedido, pedidoDTO);
		pedidoRepository.save(pedido);
		salvarListaVendas(pedido.getItens());
		produtoService.atualizarEstoqueVenda(pedido);
		emailService.emailCliente(pedidoDTO, pedido);
		return "Pedido salvo com sucesso";
	}

	public PedidoDTO buscarPorId(Integer idPedido) throws PedidoException {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(idPedido);

		if (pedidoOptional.isPresent()) {
			Pedido pedido = pedidoOptional.get();
			PedidoDTO pedidoDTO = new PedidoDTO();
			return toDTO(pedidoDTO, pedido);
		}
		throw new PedidoException("Pedido não encontrado.");

	}

	public void delete(Integer idPedido) throws PedidoException {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(idPedido);

		if (pedidoOptional.isPresent()) {
			pedidoRepository.deleteById(idPedido);
		}
		throw new PedidoException("Pedido não encontrado.");
	}

	public String atualizar(Integer idPedido, PedidoDTO pedidoDTO) throws PedidoException {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(idPedido);

		if (pedidoOptional.isPresent()) {
			Pedido pedido = pedidoOptional.get();
			if (!pedidoDTO.getItens().isEmpty()) {
				List<VendasItem> itens = new ArrayList<>();
				for (ItemDTO itemDTO : pedidoDTO.getItens()) {
					VendasItem item = new VendasItem();
					item = vendasItemService.toModel(item, itemDTO);
					itens.add(item);
				}
				pedido.setItens(itens);
				pedidoRepository.save(pedido);
			}
			return "Pedido " + pedido.getIdPedido() + " foi atualizado.";
		}
		throw new PedidoException("O pedido não foi atualizado");
	}

	public List<PedidoDTO> todosPedidos() {
		List<Pedido> lisPedidos = pedidoRepository.findAll();
		List<PedidoDTO> pedidoDTOs = new ArrayList<>();

		for (Pedido pedido : lisPedidos) {
			PedidoDTO pedidoDTO = new PedidoDTO();
			pedidoDTO = toDTO(pedidoDTO, pedido);
			pedidoDTOs.add(pedidoDTO);
		}
		return pedidoDTOs;
	}
	
}
