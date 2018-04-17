package com.ipartek.formacion.nidea.pojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipartek.formacion.nidea.ejemplos.ComparatorOrdenables;
import com.ipartek.formacion.nidea.util.Utilidades;

public class OrdenableTest {

	static ArrayList<Ordenable> coleccion;
	static Mesa mesa1;
	static Mesa mesa2;
	static Perro perro1;
	static Perro perro2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		coleccion = new ArrayList<Ordenable>();
		mesa1 = new Mesa();
		mesa2 = new Mesa();
		perro1 = new Perro();
		perro2 = new Perro();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		coleccion = null;
		mesa1 = null;
		mesa2 = null;
		perro1 = null;
		perro2 = null;
	}

	@Before
	public void setUp() throws Exception {
		coleccion = new ArrayList<Ordenable>();
		mesa1 = new Mesa();
		mesa2 = new Mesa();
		perro1 = new Perro();
		perro2 = new Perro();

		mesa1.setNumeroPatas(3);
		mesa2.setNumeroPatas(9);
		perro1.setNumVacunaciones(0);
		perro2.setNumVacunaciones(1);
		coleccion.add(mesa1);
		coleccion.add(perro1);
		coleccion.add(perro2);
		coleccion.add(mesa2);
	}

	@After
	public void tearDown() throws Exception {
		coleccion.clear();
		mesa1 = null;
		mesa2 = null;
		perro1 = null;
		perro2 = null;
	}

	@Test
	public void testBubbleSort() {
		Utilidades.bubbleSort(coleccion);

		assertEquals(0, coleccion.get(0).getValor());
		assertEquals(1, coleccion.get(1).getValor());
		assertEquals(3, coleccion.get(2).getValor());
		assertEquals(9, coleccion.get(3).getValor());

		Ordenable elemento = coleccion.get(0);

		if (elemento instanceof Mesa) {
			Mesa m = (Mesa) elemento;

		} else if (elemento instanceof Perro) {
			Perro p = (Perro) elemento;

		} else {
			fail("No esperabamos esta clase de objeto");
		}
	}

	@Test
	public void testCollectionSort() {
		Collections.sort(coleccion, new ComparatorOrdenables());

		assertEquals(0, coleccion.get(0).getValor());
		assertEquals(1, coleccion.get(1).getValor());
		assertEquals(3, coleccion.get(2).getValor());
		assertEquals(9, coleccion.get(3).getValor());
	}

	class SortbyValor implements Comparator<Ordenable> {
		// Used for sorting in ascending order of
		// roll number
		public int compare(Ordenable a, Ordenable b) {
			return a.getValor() - b.getValor();
		}
	}
}
