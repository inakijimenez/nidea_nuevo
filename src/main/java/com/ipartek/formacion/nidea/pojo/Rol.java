package com.ipartek.formacion.nidea.pojo;

import javax.validation.constraints.Min;

public class Rol {

	@Min(value = 1, message = "Seleccione un rol valido")
	private int id;
	private String nombre;

	public Rol() {
		super();
		this.id = -1;
	}

	public Rol(int id, String nombre) {
		this();
		this.id = id;
		this.nombre = nombre;
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

	@Override
	public String toString() {
		return "Rol [id=" + id + ", nombre=" + nombre + "]";
	}

}
