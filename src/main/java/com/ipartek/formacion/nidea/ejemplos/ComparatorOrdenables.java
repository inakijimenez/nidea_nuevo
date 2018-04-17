package com.ipartek.formacion.nidea.ejemplos;

import java.util.Comparator;

import com.ipartek.formacion.nidea.pojo.Ordenable;

public class ComparatorOrdenables implements Comparator<Ordenable> {

	@Override
	public int compare(Ordenable o1, Ordenable o2) {

		return o1.getValor() - o2.getValor();
	}

}
