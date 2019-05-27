package br.com.provametatron.crudfuncionario.dados.persistencia;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.Query;

import br.com.provametatron.crudfuncionario.dados.hibernate.HibernateUtil;

public abstract class FuncionarioTemplate<P> {
	
	protected String selectQueryStart = "SELECT new map"
			+ "(u.nome as nome, c.nome as nomeCargo, t.ddd as dddTelefone, t.numero as numeroTelefone) FROM "
			+ "Telefone as t  INNER JOIN t.usuario as u INNER JOIN u.cargo as c";
	
	protected  abstract void adicionaParametro(Query q, P param);
	
	public final List<Map<String, String>> listar(String query,P param){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = HibernateUtil.getSessionFactory().getCurrentSession().createQuery(query);
		adicionaParametro(q,param);
		List<Map<String, String>> resultList = q.getResultList();
		session.getTransaction().commit();
		session.close();
		return resultList;
	}


}
