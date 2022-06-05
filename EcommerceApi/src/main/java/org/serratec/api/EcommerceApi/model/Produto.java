package org.serratec.api.EcommerceApi.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "produto")
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "produto_cd_id")
	private Integer idProduto;
	
	@Column(name = "produto_tx_nome")
	private String nome;
	
	@Column(name = "produto_tx_descricao")
	private String descricao;
	
	@Column(name = "produto_db_valor_unitario")
	private Double valorUnitario;
	
	@Column(name = "produto_dt_data_validade")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataValidade;
	
	@Column(name = "produto_nu_qtd_estoque")
	private Integer qtdEstoque;
	
	@Column(name = "produto_dt_data_cadastro")
	private LocalDate dataCadastro;
	
	@ManyToOne
	private Categoria categoria;
	
	public Produto() {
		super();
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public void tirarEstoque(Integer qtd) {
		this.qtdEstoque -= qtd;
	}

	public void colocarEstoque(Integer qtd) {
		this.qtdEstoque += qtd;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
