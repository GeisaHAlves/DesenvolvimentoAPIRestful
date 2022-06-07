package org.serratec.api.EcommerceApi.repository;

import java.util.List;

import org.serratec.api.EcommerceApi.DTO.RelatorioDTO;
import org.serratec.api.EcommerceApi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	@Query(value="select \r\n"
			+ "p.PRODUTO_TX_NOME as nomeProduto,\r\n"
			+ "sum(v.VENDAS_ITEM_NU_QUANTIDADE) as quantidadeVendida,\r\n"
			+ "sum(v.VENDAS_ITEM_DB_PRECO) as valorTotal\r\n"
			+ "from PRODUTO p\r\n"
			+ "join VENDAS_ITEM v\r\n"
			+ "on (v.PRODUTO_ID=p.PRODUTO_CD_ID )\r\n"
			+ "group by nomeProduto \r\n"
			+ "order by quantidadeVendida\r\n"
			+ "desc\r\n"
			+"limit 5", nativeQuery = true)
	public List<RelatorioDTO> relatorio();
}
