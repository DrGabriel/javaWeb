package br.com.provametatron.crudfuncionario.controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import br.com.provametatron.crudfuncionario.dados.persistencia.FuncionarioPersistencia;
import br.com.provametatron.crudfuncionario.dominio.Cargo;
import br.com.provametatron.crudfuncionario.dominio.Funcionario;
import br.com.provametatron.crudfuncionario.dominio.Telefone;
import br.com.provametatron.crudfuncionario.dominio.TipoDado;
import br.com.provametatron.crudfuncionario.dominio.Usuario;

public class FuncionarioController {

	private static List<Funcionario> build(List<Map<String, String>> list) {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		for (Map<String, String> funcionario : list) {
			funcionarios.add(new Funcionario(funcionario.get("nome"), funcionario.get("nomeCargo"),
					funcionario.get("dddTelefone"), funcionario.get("numeroTelefone")));
		}
		return funcionarios;
	}

	public static List<Funcionario> listaFuncionarios() {
		return build(FuncionarioPersistencia.listaFuncionarios());
	}

	public static List<Funcionario> listaFuncionarios(String nome, TipoDado tipoDado) {

		if (tipoDado == TipoDado.NOMEUSUARIO) {

			Usuario user = new Usuario();
			user.setNome(nome);
			return build(FuncionarioPersistencia.buscaFuncionario(user));
		} else if (tipoDado == TipoDado.NOMECARGO) {
			Cargo cargo = new Cargo();
			cargo.setNome(nome);
			return build(FuncionarioPersistencia.buscaFuncionario(cargo));
		}
		return Collections.emptyList();

	}

	public static List<Funcionario> listaFuncionarios(String nomeUsuario, String nomeCargo) {
		Usuario user = new Usuario();
		user.setNome(nomeUsuario);
		Cargo cargo = new Cargo();
		cargo.setNome(nomeCargo);
		return build(FuncionarioPersistencia.buscaFuncionario(user, cargo));

	}

	public static void criarFuncionario(Funcionario funcionario) {
		Usuario usuario = new Usuario();
		Cargo cargo = new Cargo();
		Telefone telefone = new Telefone();
		cargo.setNome(funcionario.getNomeCargo());
		usuario.setNome(funcionario.getNomeUsuario());
		usuario.setCargo(cargo);
		telefone.setDdd(funcionario.getDddTelefone());
		telefone.setNumero(funcionario.getNumeroTelefone());
		telefone.setUsuario(usuario);
		
		FuncionarioPersistencia.criar(cargo,usuario,telefone);
		
	}
}
