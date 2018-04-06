package com.ipartek.formacion.nidea.pojo;

public class Bebida {

	private int id;
	private String nombre;
	private float precio;

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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Bebida [id=" + id + ", nombre=" + nombre + ", precio=" + precio + "]";
	}

}
