package com.ipartek.formacion.nidea.ejemplos;

public abstract class ObjetoGrafico implements Imprimible {

	int x;
	int y;

	public ObjetoGrafico() {
		super();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public String toString() {
		return "ObjetoGrafico [x=" + x + ", y=" + y + "]";
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	void mover() {
		System.out.println("Movimiento ");
	}

	abstract void dibujar();

}
