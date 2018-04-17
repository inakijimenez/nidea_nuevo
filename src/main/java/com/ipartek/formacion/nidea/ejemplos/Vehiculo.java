package com.ipartek.formacion.nidea.ejemplos;

public abstract class Vehiculo {

	private int puertas;
	private String color;

	public Vehiculo() {
		super(); // java.lang.object
		this.puertas = 3;
		this.color = "blanco";
		System.out.println("Vehiculo nuevo instanciado");
	}

	public int getPuertas() {
		return puertas;
	}

	public void setPuertas(int puertas) {
		this.puertas = puertas;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public abstract void arrancar();

	public void encenderLuces() {
		System.out.println("luces on");
	}

	protected static void dimeMatricula() {
		// metodo tonto para probar protected
		System.out.println("matricula");
	}

	@Override
	public String toString() {
		return "Vehiculo [puertas=" + puertas + ", color=" + color + "]";
	}

}
