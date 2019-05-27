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
@Table(name="Usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	/*Não é explícito se um usuário obrigatoriamente deve possuir um cargo
	 * então o campo é obrigatório
	 * caso não fosse seria apenas necessário colocar uma estratégia no front para mostrar que caso o cargo seja null
	 * mostrar que o usuario não possui cargo
	 */
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="id_cargo")
	private Cargo cargo;
	
	@Column(name="nome", nullable=false, length = 50)
	private String nome;
	
	public Usuario() {
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
