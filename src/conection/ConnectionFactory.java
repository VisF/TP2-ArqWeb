package conection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * ConnectionFactory es una clase singleton que gestiona las conexiones
 * a las bases de datos usando JPA (Java Persistence API).
 * 
 * Soporta las bases de datos Derby y MySQL.
 */
public class ConnectionFactory {
	public static final String DERBY = "Derby";
	public static final String MYSQL = "MySQL";
	// Instancia única de la clase
	private static ConnectionFactory instance = new ConnectionFactory();
	private EntityManager em;
	private EntityManagerFactory emf;
	/**
     * Constructor privado para implementar el patrón Singleton.
     */
	private ConnectionFactory() {
	}
	
	/**
     * Obtiene la única instancia de la clase ConnectionFactory.
     *
     * @return la instancia de ConnectionFactory.
     */
	public static ConnectionFactory getInstance() {
		return instance;
	}
	
	/**
     * Conecta a la base de datos especificada.
     *
     * @param type El tipo de base de datos al que se desea conectar (Derby o MySQL).
     * @return el EntityManager asociado a la conexión.
     */
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

	 /**
     * Obtiene la conexión actual.
     *
     * @return el EntityManager actual.
     */
	public EntityManager connection() {
		return this.em;
	}

	/**
     * Desconecta y cierra las conexiones abiertas.
     */
	public void disconnect() {
		this.emf.close();
		this.em.close();
	}
}
