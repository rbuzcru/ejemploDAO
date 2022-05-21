package Ejercicio7_2.Ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Empresa {
	private static Connection conexion;

	public static void main(String[] args) throws SQLException {
		Empleado emp1 = new Empleado("01","Michael","Scott", 2500);
		Empleado emp2 = new Empleado("02","Jim","Halpert",1500);
		conectarBaseDatos();
		// primero borro los dos empleados que inserto para que no se repitan
		borrar("01");
		borrar("02");
		System.out.println("Inserto dos empleados y los busco para imprimir: ");
		insertar(emp1);
		insertar(emp2);
		buscar("01");
		buscar("02");
		System.out.println("Despues de ascender a Jim y actualizar su sueldo: ");
		actualizar("02", 2500);
		buscar("02");
		

	}

	public static void conectarBaseDatos() {
		String host = "localhost";
		int puerto = 3306;
		String database = "empleados";
		String url = "jdbc:mysql://" + host + ":" + puerto + "/" + database;
		String user = "root";
		String password = "";

		try {
			conexion = DriverManager.getConnection(url, user, password);
			System.out.println((conexion != null) ? "CONEXION OK" : "CONEXION FALLIDA");
			
			  if (conexion != null) { System.out.println("CONEXION OK"); 
			  }
			  else {
			  System.out.println("CONEXION FALLIDA"); }
			 

		} catch (java.sql.SQLException sqle) {
			System.out.println("Error: " + sqle);
		}
		

	}
	
	/*List<Empleado> getEmpleados(): consulta la lista completa de empleados y la devuelve*/
	
	ArrayList<Empleado>listaEmpleados = new ArrayList<>();
	
	public ArrayList<Empleado>getEmpleado(){
		return listaEmpleados;
		
		}
	
	/*boolean insertar(Empleado empleado): inserta un empleado en la base de datos y devuelve true si se consiguió insertar.*/
	public static boolean insertar(Empleado empleado) throws SQLException {
		PreparedStatement insercion= conexion.prepareStatement("INSERT INTO empleados(DNI,NOMBRE,APELLIDOS,SUELDO) values(?,?,?,?)");
		insercion.setString(1, empleado.getDni());
		insercion.setString(2, empleado.getNombre());
		insercion.setString(3, empleado.getApellido());
		insercion.setInt(4, empleado.getSueldo());
		insercion.executeUpdate();
		return true;
	}
	
	/*Empleado buscar(String dni): devuelve un empleado a partir de su DNI, buscándolo en la base de datos, o null si no lo encuentra.*/
	public static void buscar(String dni) throws SQLException {
		ResultSet rs = conexion.prepareStatement("SELECT * FROM empleados WHERE DNI='"+dni+"'").executeQuery();
		while (rs.next()) {
			System.out.print(rs.getString("DNI")+", ");
			System.out.print(rs.getString("NOMBRE")+", ");
			System.out.print(rs.getString("APELLIDOS")+", ");
			System.out.println(rs.getInt("SUELDO"));
		}
	}
		
	/*boolean borrar(String dni): borra un empleado a partir de su DNI devolviendo true, o false si no es posible.*/
	public static boolean borrar(String dni) throws SQLException {
		Statement ol = conexion.createStatement();
		String rs = ("DELETE FROM empleados WHERE DNI='"+dni+"'");
		ol.executeUpdate(rs);
		return true;
	}
	
	public static boolean actualizar(String dni, int sueldo) throws SQLException {
		Statement ol = conexion.createStatement();
		String rs = ("UPDATE empleados SET SUELDO = " + sueldo + " WHERE DNI = '"+ dni +"'");
		ol.executeUpdate(rs);
		return true;
	}
}
	/*
	 * "insert into empleados values('" + empleado.getDni() + "', '" +
	 * empleado.getNombre() + "', '" + empleado.getApellido() + "', " +
	 * empleado.getSueldo() + ", "+empleado.getId();
	 */