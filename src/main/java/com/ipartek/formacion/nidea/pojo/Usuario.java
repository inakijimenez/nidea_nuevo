package com.ipartek.formacion.nidea.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Usuario {

	@NotNull
	private int id;

	@NotNull
	@Size(min = 1, max = 45)
	private String nombre;

	@NotNull
	private String password;

	private Rol rol;

	public static final int ROL_ADMIN = 1;
	public static final int ROL_USER = 2;

	public Usuario() {
		super();
		this.id = -1;
		this.nombre = "Unknown";
		this.rol = new Rol();
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

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", rol=" + rol + "]";
	}

}
