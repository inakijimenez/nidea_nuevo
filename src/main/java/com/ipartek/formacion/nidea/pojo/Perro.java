package com.ipartek.formacion.nidea.pojo;

public class Perro implements Ordenable {

	private int numVacunaciones;

	public int getNumVacunaciones() {
		return numVacunaciones;
	}

	public void setNumVacunaciones(int numVacunaciones) {
		this.numVacunaciones = numVacunaciones;
	}

	@Override
	public int getValor() {
		return numVacunaciones;
	}

}
