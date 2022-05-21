package Ejercicio7_2.Ejercicio3.persistencia;

import java.util.ArrayList;

import Ejercicio7_2.Ejercicio3.empleados.Empleadob;

/**
 * @author davil
 *
 */
public interface EmpleadoDAO {
	public ArrayList<Empleadob> getEmpleados();
	public boolean nuevoEmpleado(Empleadob empleado);
	public Empleadob buscar(String dni);
	public boolean borrarEmpleado(String dni);
	public boolean actualizar(String dni, int sueldo);
}
