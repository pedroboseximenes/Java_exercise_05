package modelo;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import util.Util;

@NamedQueries({ @NamedQuery(name = "Passagem.recuperaListaPassagems", query = "select l from Passagem l order by l.id"),
		@NamedQuery(name = "Passagem.recuperaUltimaPassagem", query = "select l from Passagem l where l.cliente = ?1 order by l.id desc")
//		@NamedQuery(name = "Passagem.recuperaUmLanceComProduto", query = "select l from Passagem l left outer join fetch l.cliente where l.id = ?1")
})

/* ==> Falta acrescentar a busca Lance.recuperaUmLanceComProduto */

@Entity
@Table(name = "PASSAGEM")

public class Passagem {
	private Long id;
	private double valor;
	private String destino;
	private Calendar dataCriacao;

	// Um lance se refere a um único produto

	private Cliente cliente;
	private Voo voo;

	// ********* Construtores *********

	public Passagem() {
	}

	public Passagem(double valor, Calendar dataCriacao, String destino) {
		this.valor = valor;
		this.dataCriacao = dataCriacao;
		this.destino = destino;
	}

	public Passagem(double valor, Calendar dataCriacao, Cliente cliente, String destino, Voo voo) {
		this.valor = valor;
		this.dataCriacao = dataCriacao;
		this.cliente = cliente;
		this.destino = destino;
		this.voo = voo;
	}

	// ********* Métodos do Tipo Get *********

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")

	public Long getId() {
		return id;
	}

	public double getValor() {
		return valor;
	}

	@Column(name = "DATA_CRIACAO")
	@Temporal(TemporalType.DATE)
	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	@Transient
	public String getDataCriacaoMasc() {
		return Util.calendarToStr(dataCriacao);
	}

	// ********* Métodos do Tipo Set *********

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	// ********* Métodos para Associações *********

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENTE_ID")
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	

	@Column(name = "DESTINO")
	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voo_id")
	public Voo getVoo() {
		return voo;
	}

	public void setVoo(Voo voo) {
		this.voo = voo;
	}


}