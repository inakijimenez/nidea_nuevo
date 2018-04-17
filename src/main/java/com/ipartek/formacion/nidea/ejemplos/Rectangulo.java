package com.ipartek.formacion.nidea.ejemplos;

public class Rectangulo extends ObjetoGrafico {

	@Override
	public void imprimir() {
		System.out.println("Imprimiendo Rectangulo");
	}

	@Override
	void dibujar() {
		System.out.println("Dibujando Rectangulo");
	}

	@Override
	void mover() {
		System.out.println("Moviendo Rectangulo");
	}

}
