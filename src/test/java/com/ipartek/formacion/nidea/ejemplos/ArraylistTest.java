package com.ipartek.formacion.nidea.ejemplos;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArraylistTest {

	static ArrayList<String> al;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		al = new ArrayList<String>();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		al = null;
	}

	@Before
	public void setUp() throws Exception {
		al.add("España");
		al.add("Portugal");
		al.add("Francia");
	}

	@After
	public void tearDown() throws Exception {
		al = null;
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
