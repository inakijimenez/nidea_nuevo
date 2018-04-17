package com.ipartek.formacion.nidea.ejemplos;

public class VehiculoElectrico extends Vehiculo {

	private float potencia; // Kw

	public VehiculoElectrico() {
		super();
		this.potencia = 0;
		System.out.println("Instanciado VehiculoElectrico");
	}

	public VehiculoElectrico(float potencia) {
		this(); // cambiar super por this
		this.potencia = potencia;
	}

	public float getPotencia() {
		return potencia;
	}

	public void setPotencia(float potencia) {
		this.potencia = potencia;
	}

	@Override
	public void arrancar() {
		System.out.println("Pulsar boton de encendido");
	}

	@Override
	public String toString() {
		return super.toString() + "VehiculoElectrico [potencia=" + potencia + "]";
	}

	public void electrico() {
		System.out.println("soy electrico");
	}

}
