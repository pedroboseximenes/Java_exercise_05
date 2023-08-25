package modelo;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="VOO")
public class Voo {
	private Long id;
	private String assento;
	private String portao;
	private Calendar data_voo;
	
	public Voo() {
		
	}
	
	public Voo(Long id, String assento, String portao, Calendar data_voo) {
		this.id = id;
		this.assento = assento;
		this.portao = portao;
		this.data_voo = data_voo;
	}
	
	
	private List<Passagem> passagems = new ArrayList<Passagem>();
	
	
	@Column(name = "ASSENTO	")
	public String getAssento() {
		return assento;
	}
	public void setAssento(String assento) {
		this.assento = assento;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "DATA_VOO")
	@Temporal(TemporalType.DATE)
	public Calendar getData_voo() {
		return data_voo;
	}
	public void setData_voo(Calendar data_voo) {
		this.data_voo = data_voo;
	}
	
	@Column(name = "PORTAO")
	public String getPortao() {
		return portao;
	}
	public void setPortao(String portao) {
		this.portao = portao;
	}
	
	

	@OneToMany(mappedBy = "voo")
	public List<Passagem> getPassagems() {
		return passagems;
	}
	public void setPassagems(List<Passagem> passagems) {
		this.passagems = passagems;
	}
	

}
