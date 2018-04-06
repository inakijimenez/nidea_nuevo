package com.ipartek.formacion.nidea.depuracion;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utilidades {

	public static String diaHoy() {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return localDate.toString();
	}

}
