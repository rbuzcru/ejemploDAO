package Ejercicio7_2.Ejercicio3.persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import Ejercicio7_2.Ejercicio3.empleados.Empleadob;
import Ejercicio7_2.Ejercicio3.empleados.Principal;
import Ejercicio7_2.Ejercicio3.persistencia.EmpleadoDAOBaseDatos;

public class GestionEmpleado {
	
	private EmpleadoDAO emdao;
	private ArrayList<Empleadob> listaEmpleados = new ArrayList<Empleadob>();
	
	public GestionEmpleado(String tipoDao) {

		if (tipoDao == "Base datos") {
			emdao = new EmpleadoDAOBaseDatos();
		} else if (tipoDao == "Csv") {
			emdao = new EmpleadoDAOFichero();
		}
	}
	

	/*
	 * Empleado getEmpleado(String dni): devuelve un empleado con el DNI
	 * especificado. Si no está en la lista, lo busca en la base de datos. En caso
	 * de no existir, devuelve null.
	 */
	public Empleadob getEmpleados(String dniBuscado) throws SQLException {
		Empleadob empBuscado = null;
		for (Empleadob empleado : listaEmpleados) {
			if (empleado.getDni().contains(dniBuscado)) {
				empBuscado = empleado;
			}
		}
		if (empBuscado == null) {
			empBuscado = emdao.buscar(dniBuscado);

		}
		return empBuscado;
	}

	/*
	 * boolean nuevoEmpleado(Empleado empleado): inserta un empleado a la lista de
	 * empleados y lo guarda en la base de datos.
	 */
	public boolean nuevoEmpleado(Empleadob empleado) throws SQLException {
		listaEmpleados.add(empleado);
		return emdao.nuevoEmpleado(empleado);
	}

	/*
	 * boolean borrarEmpleado(String dni): elimina un empleado de la lista y lo
	 * borra de la base de datos. Si el empleado no está en la lista, lo busca en la
	 * base de datos. En caso de no existir, devuelve false.
	 */
	public boolean borrarEmpleado(String dni) throws SQLException {
		Iterator<Empleadob> iterador = listaEmpleados.iterator();
		while (iterador.hasNext()) {
			if (iterador.next().getDni().contains(dni)) {
				iterador.remove();
			}
		}
		return emdao.borrarEmpleado(dni);
	}

	/*
	 * boolean modificarSueldo(String dni, double sueldo): modifica el sueldo del
	 * empleado y modifica la base de datos.
	 */
	public boolean modificarEmpleado(String dni, int sueldo) throws SQLException {
		for (Empleadob emps : listaEmpleados) {
			if (emps.getDni().contains(dni)) {
				emps.setSueldo(sueldo);
			}
		}
		return emdao.actualizar(dni, sueldo);
	}
}
