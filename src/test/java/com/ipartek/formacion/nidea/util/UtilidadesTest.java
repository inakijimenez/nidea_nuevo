package com.ipartek.formacion.nidea.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ipartek.formacion.nidea.util.Utilidades;

public class UtilidadesTest {

	@Test
	public void testLimpiarEspacios() {

		assertEquals("hola que ase", Utilidades.limpiarEspacios("   hola    que    ase    "));
		assertEquals("", Utilidades.limpiarEspacios(null));
	}

}
