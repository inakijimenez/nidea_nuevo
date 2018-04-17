package com.ipartek.formacion.nidea.ejemplos;

public class Circulo extends ObjetoGrafico {

	private int radio;

	public Circulo() {
		super();
		this.radio = 0;
	}

	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}

	@Override
	public void imprimir() {
		System.out.println("Imprimir Circulo");
	}

	@Override
	void dibujar() {
		System.out.println("Dibujando Circulo ");
	}

	@Override
	void mover() {
		System.out.println("Moviendo Circulo");
	}

}
