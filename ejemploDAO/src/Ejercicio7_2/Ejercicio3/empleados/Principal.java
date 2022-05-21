package Ejercicio7_2.Ejercicio3.empleados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import Ejercicio7_2.Ejercicio3.persistencia.EmpleadoDAOBaseDatos;
import Ejercicio7_2.Ejercicio3.persistencia.GestionEmpleado;

public class Principal {

	public static void main(String[] args) throws SQLException {
		GestionEmpleado gestorBD = new GestionEmpleado("Base datos");
		GestionEmpleado gestorF = new GestionEmpleado("Csv");
		
		Empleadob emp1 = new Empleadob("123456789A", "Antonio", "Rodr�guez Ant�nez", 1200);
		Empleadob emp2 = new Empleadob("234567890B", "Pedro", "S�nchez Guiti�rrez", 1500);
		Empleadob emp3 = new Empleadob("345678901C", "Dolores", "Rubio Delgado", 1100);
		Empleadob emp4 = new Empleadob("456789012D", "Juan", "P�rez Garc�a", 1800);

		/* Con ficheros */

		gestorF.nuevoEmpleado(emp1);
		gestorF.nuevoEmpleado(emp2);
		gestorF.nuevoEmpleado(emp3);
		gestorF.nuevoEmpleado(emp4);
		
		System.out.println(gestorF.getEmpleados("345678901C"));
		


		/* Con base de datos */
		EmpleadoDAOBaseDatos emdaobd = new EmpleadoDAOBaseDatos();

		gestorBD.borrarEmpleado("123456789A");
		gestorBD.borrarEmpleado("234567890B");
		gestorBD.borrarEmpleado("345678901C");

		gestorBD.nuevoEmpleado(emp1);
		gestorBD.nuevoEmpleado(emp2);
		gestorBD.nuevoEmpleado(emp3);
		
		System.out.println(gestorBD.getEmpleados("456789012D"));
		
	}


}
