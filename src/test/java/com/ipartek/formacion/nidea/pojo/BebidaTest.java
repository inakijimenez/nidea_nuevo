package com.ipartek.formacion.nidea.pojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BebidaTest {

	static ValidatorFactory factory;
	static Validator validator;
	static Bebida bebida;
	static int fallos;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Crear Factoria y Validador
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Crear Factoria y Validador
		factory = null;
		validator = null;
	}

	@Before
	public void setUp() throws Exception {
		bebida = new Bebida();
		fallos = 0;
	}

	@After
	public void tearDown() throws Exception {
		bebida = null;
		fallos = 0;
	}

	@Test
	public void testConstructor() {

		fallos = validar(bebida);

		assertTrue(fallos == 2);
	}

	@Test
	public void testPrecio() {

		bebida.setPrecio(0.1f);
		fallos = validar(bebida);
		assertTrue(fallos == 1);

		bebida.setPrecio(17f);
		fallos = validar(bebida);
		assertTrue(fallos == 1);
	}

	@Test
	public void testNombre() {

		bebida.setNombre("AAA");
		fallos = validar(bebida);
		assertTrue(fallos == 1);

		bebida.setNombre(null);
		fallos = validar(bebida);
		assertTrue(fallos == 2);

		String str = "";
		for (int i = 0; i < 45; i++) {
			str += "a";
		}
		bebida.setNombre(str);
		fallos = validar(bebida);
		assertTrue(fallos == 1);

		str = "";
		for (int i = 0; i < 46; i++) {
			str += "a";
		}
		bebida.setNombre(str);
		fallos = validar(bebida);
		assertTrue(fallos == 2);
	}

	public int validar(Bebida bebida) {
		// Obtener las ConstrainViolation
		Set<ConstraintViolation<Bebida>> violations = validator.validate(bebida);
		return violations.size();
	}

	@Test
	public void validar() {

		try {

			// Crear Factoria y Validador
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			// Crear Objeto a validar
			Bebida bebida = new Bebida();

			// Obtener las ConstrainViolation
			Set<ConstraintViolation<Bebida>> violations = validator.validate(bebida);
			assertTrue(violations.size() == 2);

			bebida.setPrecio(0.1f);
			violations = validator.validate(bebida);
			assertTrue(violations.size() == 1);

			bebida.setNombre("ab");
			violations = validator.validate(bebida);
			Iterator<ConstraintViolation<Bebida>> it = violations.iterator();
			ConstraintViolation<Bebida> violation = null;
			while (it.hasNext()) {
				violation = it.next();
				assertEquals("nombre", violation.getPropertyPath().toString()); // saca el nombre de la variable
				assertEquals("ab", violation.getInvalidValue()); // saca el valor de la variable
				violation.getMessage(); // saca el mensaje de error
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

}
