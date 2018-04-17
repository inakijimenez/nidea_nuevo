package com.ipartek.formacion.nidea.util;

import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.nidea.pojo.Ordenable;

public class Utilidades {

	/**
	 * Metodo estatico para poder usarse desde la propia clase sin tener que instanciar un objeto
	 * 
	 * Limpia los caracteres vacios " " de una cadena String
	 * Hacemos trim() ademas de sustituir todos los espacios en blanco por uno unico
	 * 
	 * " Hola que ase? " => "Hola que ase?"
	 * 
	 * @param str
	 * @return en caso de null retorna ""
	 */
	public static String limpiarEspacios(String str) {
		String resultado = "";

		if (null != str) {
			resultado = str.trim();
			resultado = resultado.replaceAll("\\s+", " ");
		}

		return resultado;
	}

	/**
	 * Metodo para ordenar una coleccion con el algoritmo bubbleshort
	 * Ordena de menor a mayor basandose en el metodo getValor() de la interfaz Ordenable
	 * 
	 * @see com.ipartek.formacion.nidea.pojo.Ordenable
	 * @param list
	 *            List<Ordenable> coleccion con los elementos a ordenar
	 * @return si es null devuelve una lista vacia
	 */
	public static List<Ordenable> bubbleSort(List<Ordenable> list) {
		List<Ordenable> resultado = new ArrayList<Ordenable>();

		if (list != null) {
			resultado = list;
			Ordenable temp = null;
			for (int i = 0; i < resultado.size(); i++) {
				for (int j = 1; j < (resultado.size() - i); j++) {
					if (resultado.get(j - 1).getValor() > resultado.get(j).getValor()) {
						// swap elements
						temp = resultado.get(j - 1);
						resultado.set(j - 1, resultado.get(j));
						resultado.set(j, temp);
					}
				}
			}
		}

		return resultado;
	}

}
