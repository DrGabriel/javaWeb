package br.com.provametatron.crudfuncionario.dados.persistencia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;

import br.com.provametatron.crudfuncionario.dados.hibernate.HibernateUtil;
import br.com.provametatron.crudfuncionario.dominio.Cargo;
import br.com.provametatron.crudfuncionario.dominio.Telefone;
import br.com.provametatron.crudfuncionario.dominio.Usuario;

public class FuncionarioPersistencia {

	public static List<Map<String, String>> listaFuncionarios() {
		 class FuncionarioSemArgumento extends FuncionarioTemplate<Object>{
			@Override
			protected void adicionaParametro(Query q, Object param) {
			}
			
		}
		FuncionarioSemArgumento func = new FuncionarioSemArgumento();
		return func.listar(func.selectQueryStart,null);
	}

	public static List<Map<String, String>> buscaFuncionario(Usuario usuario) {
		String query = " WHERE u.nome like :nomeU";
		
		 class FuncionarioComArgumento extends FuncionarioTemplate<Usuario>{
			@Override
			protected void adicionaParametro(Query q, Usuario usuario) {
				q.setParameter("nomeU", usuario.getNome()+"%");
			}
			
		}
		FuncionarioComArgumento func = new FuncionarioComArgumento();
		return func.listar(func.selectQueryStart + query,usuario);
	}

	public static List<Map<String, String>> buscaFuncionario(Cargo cargo) {
		String query = " WHERE c.nome like :nomeC";
		
		 class FuncionarioComArgumento extends FuncionarioTemplate<Cargo>{
			@Override
			protected void adicionaParametro(Query q, Cargo cargo) {
				q.setParameter("nomeC", cargo.getNome()+"%");
			}
			
		}
		FuncionarioComArgumento func = new FuncionarioComArgumento();
		return func.listar(func.selectQueryStart + query,cargo);
	}

	public static List<Map<String, String>> buscaFuncionario(Usuario usuario, Cargo cargo) {
		String query = " WHERE c.nome like :nomeC AND u.nome like :nomeU";
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("cargo", cargo);
		parameters.put("usuario", usuario);
		 class FuncionarioComArgumento extends FuncionarioTemplate<Map<String,Object>>{
			@Override
			protected void adicionaParametro(Query q, Map<String,Object> params) {
				q.setParameter("nomeC", ((Cargo) params.get("cargo")).getNome()+"%");
				q.setParameter("nomeU", ((Usuario) params.get("usuario")).getNome()+"%");
			}
			
		}
		FuncionarioComArgumento func = new FuncionarioComArgumento();
		return func.listar(func.selectQueryStart + query,parameters);
	}

	public static void criar(Cargo cargo, Usuario usuario, Telefone telefone) {
		org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 
        session.beginTransaction();
        Query query = session.             
        	    createQuery("select c from Cargo c where c.nome = :nome");
        	        query.setParameter("nome", cargo.getNome() );
       
        	        System.out.println("QUERY RESULT: " + query.uniqueResult());
    	/*Se existe cargo, como nome é unico, usa o cargo existente e seta no user
    	 * senão salva o novo cargo
    	 */
        if(query.uniqueResult() != null) {
        	cargo = (Cargo) query.uniqueResult();
        	usuario.setCargo(cargo);
        }else {
        	session.save(cargo);
        }
        
        session.saveOrUpdate(usuario);
        session.saveOrUpdate(telefone);
        session.getTransaction().commit();
        session.close();
        
	}
}
