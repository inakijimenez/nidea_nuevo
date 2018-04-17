package com.ipartek.formacion.nidea.ejemplos;

public class EjercicioVehiculos {

	public static void main(String[] args) {

		/*
		 * Vehiculo rayoMcQueen = new Vehiculo();
		 * System.out.println(rayoMcQueen.toString());
		 */

		Vehiculo tesla = new VehiculoElectrico();

		tesla.arrancar();
		System.out.println(tesla.toString());

		Vehiculo.dimeMatricula();

		((VehiculoElectrico) tesla).electrico();
	}

}
