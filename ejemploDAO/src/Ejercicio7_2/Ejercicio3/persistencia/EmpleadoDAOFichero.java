package Ejercicio7_2.Ejercicio3.persistencia;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Ejercicio7_2.Ejercicio3.empleados.Empleadob;

public class EmpleadoDAOFichero implements EmpleadoDAO {
	ArrayList<Empleadob> listaEmpleados = new ArrayList<>();
	String fichero = "src/Ejercicio3/Empleados.csv";

	/**
	 * List<Empleado> getEmpleados(): consulta la lista completa de empleados del
	 * fichero CSV y la devuelve.
	 */
	@Override
	public ArrayList<Empleadob> getEmpleados() {
		Scanner sc = null;
		String rutaFichero = fichero;
		try {
			sc = new Scanner(new File(rutaFichero));

			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				String[] separacionLineas = linea.split(",");
				Empleadob emp = new Empleadob(separacionLineas[0], separacionLineas[1], separacionLineas[2],
						Integer.valueOf(separacionLineas[3]));
				listaEmpleados.add(emp);
			}
		} catch (Exception ex) {
			System.out.println("Mensaje: " + ex.getMessage());
		} finally {
			if (sc != null)
				sc.close();
		}
		return listaEmpleados;
	}

	/**
	 * boolean nuevoEmpleado (Empleado empleado): inserta un empleado al final del
	 * archivo CSV y devuelve true si se consiguió insertar.
	 */
	@Override
	public boolean nuevoEmpleado(Empleadob empleado) {
		String dni = empleado.getDni();
		String nombre = empleado.getNombre();
		String apellidos = empleado.getApellido();
		double sueldo = empleado.getSueldo();

		String linea = dni+","+ nombre+","+ apellidos+","+ String.valueOf(sueldo);
		String rutaFichero = fichero;
		FileWriter fichero = null;
		try {
			fichero = new FileWriter(rutaFichero, true);
				fichero.write(linea + "\n");
			fichero.close();
		} catch (Exception ex) {
			System.out.println("Mensaje de la excepcion: " + ex.getMessage());
		}
		return false;
	}

	@Override
	public Empleadob buscar(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean borrarEmpleado(String dni) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actualizar(String dni, int sueldo) {
		// TODO Auto-generated method stub
		return false;
	}

}
