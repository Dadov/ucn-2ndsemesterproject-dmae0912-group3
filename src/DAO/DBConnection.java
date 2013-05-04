package DAO;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class DBConnection {

	// connection data;
	// TODO set your value
	private static final String driver = "jdbc:sqlserver://localhost:49156";
	// TODO manually made a database named 'Morocco'
	private static final String databaseName = ";databaseName=Morocco";
	private static String userName = ";user=sa";
	// TODO set your value
	private static String password = ";password=";
	private DatabaseMetaData dma;
	private static Connection con;

	// instance of the class is generated;
	private static DBConnection instance = null;

	/**
	 * singleton - we need private constructor, because we need to establish
	 * only one connection with the database;
	 */
	private DBConnection() {
		// path to the database;
		String url = driver + databaseName + userName + password;

		try { // loading the driver for SQL server;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Loading successful");
		} catch (Exception noDriver) {
			// the driver cannot be found, connection fails;
			System.out.println("Cannot find the driver");
			System.out.println(noDriver.getMessage());
		}

		try { // establishing connection to the database;
			con = DriverManager.getConnection(url);
			con.setAutoCommit(true);
			dma = con.getMetaData();

			System.out.println("Connection to " + dma.getURL());
			System.out.println("Driver " + dma.getDriverName());
			System.out.println("Database product name "
					+ dma.getDatabaseProductName());
		} catch (Exception noCon) {
			// the connection cannot be established;
			System.out
					.println("There is a problem with the connection to the database ");
			System.out.println(noCon.getMessage());
			System.out.println(url);
		}

	}

	/**
	 * gets the only existing instance of the class; singleton;
	 */
	public static DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}

	/** returns current database connection */
	public Connection getDBCon() {
		return con;
	}

	/** closes the connection to database */
	public static void closeConnection() {
		try { // closing the connection;
			con.close();
			System.out.println("Connection is closed");
		} catch (Exception e) {
			System.out
					.println("Error while trying closing the database connection"
							+ e.getMessage());
		}
	}

	/** starts transaction */
	public static void startTransaction() {
		try {
			con.setAutoCommit(false);
		} catch (Exception e) {
			System.out.println("failed to start transaction");
			System.out.println(e.getMessage());
		}
	}

	/** commits transaction */
	public static void commitTransaction() {
		try {
			con.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("failed to commit transaction");
			System.out.println(e.getMessage());
		}
	}

	/** rolls back transaction */
	public static void rollbackTransaction() {
		try {
			con.rollback();
			con.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("failed to rollback transaction");
			System.out.println(e.getMessage());
		}
	}
}