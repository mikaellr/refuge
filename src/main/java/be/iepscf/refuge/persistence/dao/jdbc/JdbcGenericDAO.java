package be.iepscf.refuge.persistence.dao.jdbc;

import be.iepscf.refuge.persistence.dao.GenericDAO;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


/**
 *
 * Attention, question de la fermeture de la connexion toujours à régler...
 * À ma connaissance :
 * - soit avant de sortir de chaque methode de dao (ouvrir/fermer, ouvrir/fermer, ouvrir/ferme...)
 * - soit en fin de requête/thread en utilisant une connection dans un ThreadLocale (voir ServletFilter et one-session-per-request)
 * - soit en utilisant un outil externe dédié (gestion de Jpa via Spring, plugin liés ramework, etc)
 *
 * @param <E>
 * @param <ID>
 */
public class JdbcGenericDAO<E, ID extends Serializable> implements GenericDAO<E, ID> {

	private Connection connection;
	private Class<E> beanClass;
	private Class<ID> idClass;

	public JdbcGenericDAO() {
		beanClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		idClass = (Class<ID>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		//System.out.println(String.format("mapping %s => %s", beanClass, idClass));
	}




	public Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = createConnection();
		} else {
			if (connection.isClosed()) {
				connection = createConnection();
			}
		}
		return connection;
	}

	/**
	 *  https://mariadb.com/kb/en/library/about-mariadb-connector-j/
	 *  DriverManager.getConnection("jdbc:mariadb://localhost:3306/DB?user=root&password=myPassword");
	 *
	 *  The legacy way of loading a JDBC driver also still works for MariaDB Connector/J. e.g.: Class.forName("org.mariadb.jdbc.Driver")
	 */
	public Connection createConnection() {
		Properties properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("persistence/persistence.properties"));
			String driver = properties.getProperty("driver", "org.mariadb.jdbc.Driver");
			String hostname = properties.getProperty("hostname", "localhost");
			String port = properties.getProperty("port", "3306");
			String database = properties.getProperty("database");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			String url = String.format("jdbc:mariadb://%s:%s/%s", hostname, port, database);
			Class.forName(driver);
			// or ? DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver())
			Connection connection = DriverManager.getConnection(url, username, password);
			return connection;
		} catch (IOException | ClassNotFoundException | SQLException e) {
			System.err.println(e);
		}
		return null;
	}

	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	protected Class<E> getBeanClass() {
		return beanClass;
	}
	
	protected String getBeanName() {
		return getBeanClass().getSimpleName();
	}

	protected Class<ID> getIdClass() {
		return idClass;
	}











	@Override
	public E find(ID id) {
		System.err.println(String.format("method %s.%s not implemented by derived class, returning null", getClass().getName(), new Exception()
				.getStackTrace()[0]
				.getMethodName()));
		return null;
	}
	
	@Override
	public List<E> findAll() {
		String sql = String.format("SELECT item FROM %s item", beanClass.getSimpleName());
		System.err.println(String.format("method %s.%s not implemented by derived class, returning null", getClass().getName(), new Exception()
				.getStackTrace()[0]
				.getMethodName()));
		return null;
	}

	@Override
	public long save(E entity) {
		System.err.println(String.format("method %s.%s not implemented by derived class, returning -1", getClass().getName(), new Exception()
				.getStackTrace()[0]
				.getMethodName()));
		return -1;
	}

	@Override
	public long update(E entity) {
		System.err.println(String.format("method %s.%s not implemented by derived class, returning -1", getClass().getName(), new Exception()
				.getStackTrace()[0]
				.getMethodName()));
		return -1;
	}

	/**
	 * @param entity
	 * @return
	 */
	@Override
	public long delete(E entity) {
		//String sql = String.format("DELETE FROM %s WHERE %s=...", beanClass.getSimpleName());
		System.err.println(String.format("method %s.%s not implemented by derived class, returning -1", getClass().getName(), new Exception()
				.getStackTrace()[0]
				.getMethodName()));
		return -1;
	}

}
