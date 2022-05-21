package Ejercicio7_2.ejercicio2.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Ejercicio7_2.ejercicio2.empleados.Empleadob;

public class EmpleadoDAOBaseDatos {
	private ConectarBD database;
	

	public EmpleadoDAOBaseDatos() {
		database = new ConectarBD();
	}
	
	public ArrayList<Empleadob> getEmpleado() {
		ArrayList<Empleadob> empleados = new ArrayList<>();
		ResultSet resultado;
		try {
			resultado = this.database.getConexion().prepareStatement("SELECT * FROM empleados").executeQuery();
			while (resultado.next()) {
				Empleadob emp = new Empleadob(resultado.getString("dni"), resultado.getString("nombre"),
						resultado.getString("apellidos"), resultado.getInt("sueldo"));
				empleados.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return empleados;

	}

	public boolean insertar(Empleadob empleado) throws SQLException {
		PreparedStatement insercion;
		try {
			insercion = database.getConexion().prepareStatement("INSERT INTO empleados(DNI,NOMBRE,APELLIDOS,SUELDO) values(?,?,?,?)");
			insercion.setString(1, empleado.getDni());
			insercion.setString(2, empleado.getNombre());
			insercion.setString(3, empleado.getApellido());
			insercion.setInt(4, empleado.getSueldo());
			insercion.executeUpdate();
			insercion.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Empleadob buscar(String dni) throws SQLException {
		Empleadob emp = null;
		PreparedStatement busqueda;
		ResultSet rs;
		try {
			busqueda = database.getConexion().prepareStatement("SELECT * FROM empleados WHERE dni = ?");
			busqueda.setString(1, dni);
			rs = busqueda.executeQuery();

			while (rs.next()) {
				String DNI = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				int sueldo = rs.getInt("sueldo");
				emp = new Empleadob(DNI, nombre, apellidos, sueldo);
				return emp;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean borrar(String dni) throws SQLException {
		PreparedStatement elim;
		try {
			elim = database.getConexion().prepareStatement("DELETE FROM empleados WHERE dni = ?");
			elim.setString(1, dni);
			elim.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean actualizar(String dni, int sueldo) throws SQLException {
		PreparedStatement act;
		try {
			act = database.getConexion().prepareStatement("UPDATE empleados SET sueldo=? WHERE dni = ?");
			act.setInt(1, sueldo);
			act.setString(2, dni);
			act.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
