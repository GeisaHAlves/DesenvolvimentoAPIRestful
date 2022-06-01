package org.serratec.backend.ProjetoBorracharia.repository;

import java.util.List;

import org.serratec.backend.ProjetoBorracharia.DTO.RelatorioDTO;
import org.serratec.backend.ProjetoBorracharia.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

	List<Servico> findTop5ByOrderByIdServicoDesc();
	

	@Query(value="select\r\n"
			+"c2.CLIENTE_TX_NOME as cliente,\r\n"
			+"c.CARRO_TX_MODELO as modelo,\r\n"
			+"s.SERVICO_TX_SERVICO_PRESTADO as servicoPrestado,\r\n"
			+"s.SERVICO_DB_VALOR as valor\r\n"
			+"from servico s join carro c on(s.carro_id=c.carro_cd_id)\r\n"
			+"join cliente c2 on(c2.cliente_cd_id=c.cliente_id)\r\n"
			+"order by s.SERVICO_CD_ID \r\n"
			+"desc \r\n"
			+"limit 5", nativeQuery = true)
	List<RelatorioDTO> relatorio();
	

}
