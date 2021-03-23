package com.epam.serdyukov.ispmanager.model.pool;

import com.epam.serdyukov.ispmanager.configuration.ConfigurationLoader;
import com.epam.serdyukov.ispmanager.configuration.DBConnectionConfig;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DB manager. Works with Apache Derby DB. 
 * Only the required DAO methods are defined!
 * 
 * @author D.Kolesnikov
 * 
 */
public class DBManagerBack {

	private static final Logger log = Logger.getLogger(DBManagerBack.class);
	private final DBConnectionConfig dbConnectionConfig;

	// //////////////////////////////////////////////////////////
	// singleton
	// //////////////////////////////////////////////////////////

	private static DBManagerBack instance;

	public static synchronized DBManagerBack getInstance() {
		if (instance == null)
			instance = new DBManagerBack();
		return instance;
	}

	/**
	 * Returns a DB connection from the Pool Connections. Before using this
	 * method you must configure the Date Source and the Connections Pool in your
	 * WEB_APP_ROOT/META-INF/context.xml file.
	 *
	 * @return A DB connection.
	 */
	public Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(dbConnectionConfig.getDbHost(),
				dbConnectionConfig.getDbUser(),
				dbConnectionConfig.getDbPass());
		log.info("DB connected");
		return con;
	}

	private DBManagerBack() {
		ConfigurationLoader configurationLoader = new ConfigurationLoader();
//		this.dbConnectionConfig = configurationLoader.getDBConfig();
		this.dbConnectionConfig =
		new DBConnectionConfig("jdbc:postgresql://localhost:5432/ispmanager",
				"postgres", "postgres");;
	}


	// //////////////////////////////////////////////////////////
	// DB util methods
	// //////////////////////////////////////////////////////////

	/**
	 * Commits and close the given connection.
	 * 
	 * @param con
	 *            Connection to be committed and closed.
	 */
	public void commitAndClose(Connection con) {
		try {
			con.commit();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Rollbacks and close the given connection.
	 * 
	 * @param con
	 *            Connection to be rollbacked and closed.
	 */
	public void rollbackAndClose(Connection con) {
		try {
			con.rollback();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}


}