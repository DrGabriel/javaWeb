package br.com.provametatron.crudfuncionario.dominio;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Telefone")
public class Telefone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@Column(name="ddd", nullable=false, length = 3)
	private String ddd;
	
	/*Tamanho 20 é utilizado pois como é um varchar apenas os caracteres preenchidos serão utilizados
	 * dito isso caso futuramente o numero de um telefone aumente, ou sua estratégia de armazenamento
	 * será possível então implementar essa mudança sem precisar alterar a estrutura da coluna
	 * há atualmente os numeros com 8 digitos
	 * 9 para celulares
	 * e os que começam com 0800
	 */
	@Column(name="numero", nullable=false, length = 20)
	private String numero;
	
	public Telefone() {}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public 	Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

}
