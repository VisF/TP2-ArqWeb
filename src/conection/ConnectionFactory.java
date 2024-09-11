package conection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	public static final String DERBY = "Derby";
	public static final String MYSQL = "MySQL";

	private static ConnectionFactory instance = new ConnectionFactory();
	private EntityManager em;
	private EntityManagerFactory emf;

	private ConnectionFactory() {
	}

	public static ConnectionFactory getInstance() {
		return instance;
	}

	public EntityManager connect(String type) {

		if (type.equals(DERBY)) {
			this.emf = Persistence.createEntityManagerFactory(DERBY);
			this.em = emf.createEntityManager();
		}

		if (type.equals(MYSQL)) {
			this.emf = Persistence.createEntityManagerFactory(MYSQL);
			this.em = emf.createEntityManager();
		}

		return this.em;
	}

	public EntityManager connection() {
		return this.em;
	}

	public void disconnect() {
		this.emf.close();
		this.em.close();
	}
}
