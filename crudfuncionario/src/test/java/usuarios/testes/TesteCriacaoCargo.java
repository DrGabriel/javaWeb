package usuarios.testes;

import static org.junit.Assert.assertEquals;

import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Test;

import br.com.provametatron.crudfuncionario.controlador.FuncionarioController;
import br.com.provametatron.crudfuncionario.dados.hibernate.HibernateUtil;
import br.com.provametatron.crudfuncionario.dominio.Cargo;

public class TesteCriacaoCargo {



	@Test
	public void test() {
		org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        Cargo cargo = new Cargo();
        cargo.setNome("teste");
        session.save(cargo);
        session.getTransaction().commit();
        session.close();
        
        //abro outra session
        session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        Query q = session.createQuery("from Cargo where nome = 'teste'");
        Cargo c = (Cargo) q.uniqueResult();
        session.getTransaction().commit();
        session.close();
        assertEquals("teste", c.getNome());
        
	}
	
	@After
	public void tearDown() throws Exception {
		org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 
        session.beginTransaction();
        Query q = session.createQuery("delete Cargo where nome = 'teste'");
        q.executeUpdate();
        session.getTransaction().commit();
        session.close();
	}

}
