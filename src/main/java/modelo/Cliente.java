package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import util.Util;

@NamedQueries({
		@NamedQuery(name = "Cliente.recuperaumClienteePassagens", query = "select p from Cliente p left outer join fetch p.passagems where p.id = ?1"),
		@NamedQuery(name = "Cliente.recuperaListaCliente", query = "select p from Cliente p order by p.id"),
		@NamedQuery(name = "Cliente.recuperaClienteePassagems", query = "select distinct p from Cliente p left outer join fetch p.passagems order by p.id asc"),
		@NamedQuery(name = "Cliente.recuperaConjuntoClientesPassagems", query = "select p from Cliente p left outer join fetch p.passagems order by p.id asc"),
///		@NamedQuery(name = "Cliente.recuperaPrimeiroProduto", query = "select p from Cliente p order by p.nome asc") })
})

@Entity
@Table(name = "CLIENTE")

public class Cliente {
	private Long id;
	private String nome;
	private String email;
	private Calendar dataCadastro;
	private Calendar dataVenda;

	// Um produto possui lances

	private List<Passagem> passagems = new ArrayList<Passagem>();

	// ********* Construtores *********

	public Cliente() {
	}

	public Cliente(String nome, String email, Calendar dataCadastro) {
		this.nome = nome;
		this.email = email;
		
		this.dataCadastro = dataCadastro;
	}

	// ********* Métodos do Tipo Get *********

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	@Column(name = "DATA_NASCIMENTO")
	@Temporal(TemporalType.DATE)
	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	@Transient
	public String getDataCadastroMasc() {
		return Util.calendarToStr(dataCadastro);
	}

	@Column(name = "DATA_VENDA")
	@Temporal(TemporalType.DATE)
	public Calendar getDataVenda() {
		return dataVenda;
	}

	@Transient
	public String getDataVendaMasc() {
		return Util.calendarToStr(dataVenda);
	}

	// ********* Métodos do Tipo Set *********

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setDataVenda(Calendar dataVenda) {
		this.dataVenda = dataVenda;
	}

	// ********* Métodos para Associações *********

	@OneToMany(mappedBy = "cliente")
	@OrderBy
	/*
	 * Com o atributo mappedBy. Sem ele a JPA irá procurar pela tabela
	 * PRODUTO_LANCE. Com ele, ao se tentar recuperar um produto e todos os seus
	 * lances, o join de PRODUTO e LANCE irá acontecer através da chave estrangeira
	 * existente em LANCE.
	 */
	public List<Passagem> getPassagems() {
		return passagems;
	}

	public void setPassagems(List<Passagem> passagems) {
		this.passagems = passagems;
	}
}
