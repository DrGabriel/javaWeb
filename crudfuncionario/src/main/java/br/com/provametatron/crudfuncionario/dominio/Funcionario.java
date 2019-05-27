package br.com.provametatron.crudfuncionario.dominio;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

/*Classe utilizada para mediar chamadas REST, um funcionario nada mais é que o JOIN das 3 tabelas do domínio
 * 
 */
public class Funcionario {
	private String nomeUsuario, nomeCargo, dddTelefone, numeroTelefone;
	
	@JsonbCreator
	public Funcionario(@JsonbProperty("nomeUsuario")String nome, 
					   @JsonbProperty("nomeCargo")String cargo, 
					   @JsonbProperty("dddTelefone")String ddd, 
					   @JsonbProperty("numeroTelefone")String telefone) {
		this.nomeUsuario = nome;
		this.nomeCargo = cargo;
		this.dddTelefone = ddd;
		this.numeroTelefone = telefone;
	
	}
	
	
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}


	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}


	public void setDddTelefone(String dddTelefone) {
		this.dddTelefone = dddTelefone;
	}


	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}


	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	public String getDddTelefone() {
		return dddTelefone;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}
				   
}
