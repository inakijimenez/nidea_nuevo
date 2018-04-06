package com.ipartek.formacion.nidea.depuracion;

public class EjercicioDepuracion {

	public static void main(String[] args) {

		for (int i = 0; i < 5000; i++) {
			System.out.println("Iteracion: " + i);
		}

		metodoA();

	}

	private static void metodoA() {
		metodoB();

	}

	private static void metodoB() {
		metodoC();

	}

	private static void metodoC() {

		String dia = Utilidades.diaHoy();
		System.out.println(dia);

	}
}
