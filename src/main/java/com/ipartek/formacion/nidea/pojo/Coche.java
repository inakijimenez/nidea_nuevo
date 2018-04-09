package com.ipartek.formacion.nidea.pojo;

public class Coche implements AutoCloseable {

	public Coche() {
		super();
		System.out.println("Creamos coche");
	}

	public void Conducir() {
		System.out.println("brumbrum estamos conduciendo");
	}

	@Override
	public void close() throws Exception {
		System.out.println("Paramos coche");
	}

	public static void main(String[] args) {

		// Si declaramos un objeto que implemente la interfaz autoclosable dentro de los parentesis de TRY, cuando llega
		// al finally se ejecuta de forma automatica su metodo close()

		try (Coche c = new Coche()) {
			System.out.println("Empezamos programa");
			c.Conducir();
		} catch (Exception e) {
			System.out.println("Tenemos una excepcion");
		} finally {
			System.out.println("Finalizamos");
		}
	}
}
