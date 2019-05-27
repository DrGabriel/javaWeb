package br.com.provametatron.crudfuncionario.rest;

import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.provametatron.crudfuncionario.controlador.FuncionarioController;
import br.com.provametatron.crudfuncionario.dominio.TipoDado;

@Path("funcionario")
public class Funcionario {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@QueryParam("nomeUsuario") String nomeUsuario,
            			   @QueryParam("nomeCargo") String nomeCargo) {

		/*
		 * Protegendo o back de inputs nulos
		 * É importante verificar no front e no back
		 */
		nomeUsuario = nomeUsuario == null? "" : nomeUsuario;
		nomeCargo = nomeCargo == null? "" : nomeCargo;
		int sizeNomeUsuario = nomeUsuario.length();
		int siszeNomeCargo = nomeCargo.length();
		
		if(sizeNomeUsuario != 0 &&siszeNomeCargo   != 0) {
			return Response.ok(FuncionarioController.listaFuncionarios(nomeUsuario,nomeCargo)).build();
		}else if(sizeNomeUsuario  != 0 && siszeNomeCargo   == 0) {
			return Response.ok(FuncionarioController.listaFuncionarios(nomeUsuario, TipoDado.NOMEUSUARIO)).build();
		}else if(sizeNomeUsuario  == 0 && siszeNomeCargo   != 0) {
			return Response.ok(FuncionarioController.listaFuncionarios(nomeCargo, TipoDado.NOMECARGO)).build();
		}
		
		return Response.ok().entity(FuncionarioController.listaFuncionarios()).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(String json) {
		
		if(json!= null) {
			br.com.provametatron.crudfuncionario.dominio.Funcionario funcionario =
					JsonbBuilder.create().fromJson(json, br.com.provametatron.crudfuncionario.dominio.Funcionario.class);
			FuncionarioController.criarFuncionario(funcionario);
		}
		
		return Response.ok(FuncionarioController.listaFuncionarios()).build();
		
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(br.com.provametatron.crudfuncionario.dominio.Funcionario json) {
		
		return null;
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(br.com.provametatron.crudfuncionario.dominio.Funcionario json) {
		
		return null;
		
	}
	
}
