package com.ipartek.formacion.nidea.pojo;

public class Usuario {

	private static int ROL_ADMIN = 1;
	private static int ROL_USER = 2;

	int id;
	String nombre;
	String password;
	int rol;

	public Usuario() {
		super();
		this.id = -1;
		this.nombre = "Unknown";
		this.rol = ROL_USER;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", rol=" + rol + "]";
	}

}
