package Ejercicio7_2.ejercicio2.empleados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import Ejercicio7_2.ejercicio2.persistencia.EmpleadoDAOBaseDatos;

public class GestionEmpleado {

	private EmpleadoDAOBaseDatos emdao = new EmpleadoDAOBaseDatos();
	private ArrayList<Empleadob> listaEmpleados = new ArrayList<Empleadob>();

	public static void main(String[] args) throws SQLException {

		Empleadob emp1 = new Empleadob("123456789A", "Antonio", "Rodríguez Antúnez", 1200);
		Empleadob emp2 = new Empleadob("234567890B", "Pedro", "Sánchez Guitiérrez", 1500);
		Empleadob emp3 = new Empleadob("345678901C", "Dolores", "Rubio Delgado", 1100);

		GestionEmpleado gestor = new GestionEmpleado();
		/* eliminando todos para que no se repitan tanto en la base de datos */
		gestor.borrarEmpleado("123456789A");
		gestor.borrarEmpleado("234567890B");
		gestor.borrarEmpleado("345678901C");

		gestor.nuevoEmpleado(emp1);
		gestor.nuevoEmpleado(emp2);
		gestor.nuevoEmpleado(emp3);

		System.out.println("\nmostrando los 3 empleados originales: ");
		gestor.imprimirEmpleados();
		
		System.out.println("\nmostrando los 3 empleados originales, junto al nuevo: ");
		Empleadob emp4 = new Empleadob("456789012D", "Juan", "Pérez García", 1800);
		gestor.nuevoEmpleado(emp4);
		gestor.imprimirEmpleados();
		
		System.out.println("\nmostrando los empleados, despues de eliminar al 123456789A(Antonio): ");
		gestor.borrarEmpleado("123456789A");
		gestor.imprimirEmpleados();
		
		System.out.println("\nmostrando los empleados, despues de eliminar a un empleado que no existe: ");
		gestor.borrarEmpleado("987654321Z");
		gestor.imprimirEmpleados();
		
		System.out.println("\nmodificando al empleado 456789012D(Juan): ");
		gestor.modificarEmpleado("456789012D", 2100);
		gestor.imprimirEmpleados();
	}

	public ArrayList<Empleadob> getListaEmpleados() {
		return listaEmpleados;
	}

	public void imprimirEmpleados() {
		for (Empleadob empleado : listaEmpleados) {
			System.out.println(empleado);
		}

	}

	/*
	 * Empleado getEmpleado(String dni): devuelve un empleado con el DNI
	 * especificado. Si no está en la lista, lo busca en la base de datos. En caso
	 * de no existir, devuelve null.
	 */
	public Empleadob getEmpleado(String dniBuscado) throws SQLException {
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
		return emdao.insertar(empleado);
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
		return emdao.borrar(dni);
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
