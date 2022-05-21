package Ejercicio7_2.ejercicio2.empleados;

public class Empleadob {
	private String dni;
	private String nombre;
	private String apellido;
	private int sueldo;

	public Empleadob(String dni, String nombre, String apellido, int sueldo) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sueldo = sueldo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public int getSueldo() {
		return sueldo;
	}

	public boolean equals(Empleadob emp) {
		return this.equals(emp);
	}

	@Override
	public String toString() {
		return "[dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", sueldo=" + sueldo + "]";
	}

}
