package com.ipartek.formacion.nidea.ejemplos;

public final class CirculoColoreado extends Circulo {

	private String color;

	public CirculoColoreado(String color) {
		super();
		this.color = "blanco";
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void imprimir() {
		System.out.println("Imprimiendo " + super.toString() + " Coloreado");
	}

	@Override
	void dibujar() {
		System.out.println("Dibujando " + super.toString() + " Coloreado");
	}

	@Override
	void mover() {
		System.out.println("Moviendo " + super.toString() + " Coloreado");
	}

}
