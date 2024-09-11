package DAO;

import conection.ConnectionFactory;

public class DAOFactory {
	
	private static DAOFactory instance = new DAOFactory();

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return instance;
	}

	public EstudianteDAO getEstudianteDAO(String type) {
		if (type.equals(ConnectionFactory.MYSQL)) {
			return new EstudianteDAOImpMySQL();
		}
		if (type.equals(ConnectionFactory.DERBY)) {
			return new EstudianteDAOImpDerby();
		}

		throw new IllegalArgumentException("Tipo de DAO no v�lido: " + type);

	}
	public CarreraDAO getCarreraDAO(String type) {
		if (type.equals(ConnectionFactory.MYSQL)) {
			return new CarreraDAOImpMySQL();
		}
		if (type.equals(ConnectionFactory.DERBY)) {
			return new CarreraDAOImpDerby();
		}

		throw new IllegalArgumentException("Tipo de DAO no v�lido: " + type);

	}
	public EstudianteCarreraDAO getEstudianteCarreraDAO(String type) {
		if (type.equals(ConnectionFactory.MYSQL)) {
			return new EstudianteCarreraDAOImpMySQL();
		}
		if (type.equals(ConnectionFactory.DERBY)) {
			return new EstudianteCarreraDAOImpDerby();
		}

		throw new IllegalArgumentException("Tipo de DAO no v�lido: " + type);

	}
}