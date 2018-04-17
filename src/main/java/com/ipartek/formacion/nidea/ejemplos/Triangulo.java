package com.ipartek.formacion.nidea.ejemplos;

public class Triangulo extends ObjetoGrafico {

	@Override
	public void imprimir() {
		System.out.println("Imprimiendo Triangulo");
	}

	@Override
	void dibujar() {
		System.out.println("Dibujando Triangulo");
	}

	@Override
	void mover() {
		System.out.println("Moviendo Triangulo");
	}

}
