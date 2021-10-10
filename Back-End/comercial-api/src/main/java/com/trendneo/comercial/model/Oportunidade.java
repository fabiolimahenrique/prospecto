package com.trendneo.comercial.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Oportunidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
	private Integer id;
	
    @NotEmpty
    @Size(max = 80)
    @Column(name="nome_prospecto", nullable = false)
    private String nomeProspecto;
	
    @NotEmpty
    @Size(max = 100)
    @Column(nullable = false)
	private String descricao;
	
    @NotEmpty
    @Size(max = 100)
    @Column(nullable = false)
	private String tipoOferta;

    @NotEmpty
    @Size(max = 4)
    @Column(nullable = false)
	private String ano;

    @NotEmpty
    @Size(max = 100)
    @Column(nullable = false)
	private String etapa;
    
    @NotEmpty
    @Size(max = 100)
    @Column(nullable = false)
	private String empresa;
    
    @NotEmpty
    @Size(max = 100)
    @Column(nullable = false)
	private String setor;
    
    @Column
    @Min(0)
	private BigDecimal valor;
   
    
	//@Column
    //@Lob
    //@Type(type = "org.hibernate.type.ImageType")
    //private byte[] file;
	
	public Integer getId() {
		return id;
	}

	public String getTipoOferta() {
		return tipoOferta;
	}

	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getNomeProspecto() {
		return nomeProspecto;
	}

	public void setNomeProspecto(String nomeProspecto) {
		this.nomeProspecto = nomeProspecto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Oportunidade other = (Oportunidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}
