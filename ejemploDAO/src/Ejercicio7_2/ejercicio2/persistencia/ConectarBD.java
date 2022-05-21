package Ejercicio7_2.ejercicio2.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectarBD {
	String host = "localhost";
	int puerto = 3306;
	String database = "empleados";
	String url = "jdbc:mysql://" + host + ":" + puerto + "/" + database;
	String user = "root";
	String password = "";
	private Connection conexion;
	

	public ConectarBD() {
		accederBD();
	}

	public void accederBD() {
		try {
			conexion = DriverManager.getConnection(url, user, password);
			System.out.println((conexion != null) ? "CONEXION OK" : "CONEXION FALLIDA");

			if (conexion != null) {
				System.out.println("CONEXION OK");
			} else {
				System.out.println("CONEXION FALLIDA");
			}

		} catch (java.sql.SQLException sqle) {
			System.out.println("Error: " + sqle);
		}

	}

	public Connection getConexion() {
		return conexion;
	}

}