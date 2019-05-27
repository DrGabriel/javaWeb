package usuarios.testes;

import static org.junit.Assert.assertNotEquals;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import br.com.provametatron.crudfuncionario.dados.hibernate.HibernateUtil;

public class TestaConexao {

	@Test
	public void test() {
	    Session session = null;
	    Transaction txn = null;
	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        txn = session.beginTransaction();
	        txn.commit();
	    } catch (RuntimeException e) {
	        if ( txn != null && txn.isActive() ) txn.rollback();
	        
	        throw e;
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    assertNotEquals(null, txn);
	}


}
