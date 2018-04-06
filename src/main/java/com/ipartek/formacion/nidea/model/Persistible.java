package com.ipartek.formacion.nidea.model;

import java.util.ArrayList;

public interface Persistible<P> {

	/**
	 * Lista una tabla de la base de datos ordenado por id descendente y limitado a
	 * 500
	 * 
	 * @return devuelve una coleccion
	 */
	public ArrayList<P> getAll();

	/**
	 * Obtemos el detalle de un registro
	 * 
	 * @param id
	 * 
	 * @return registro si existe o null en caso contrario
	 */
	public P getById(int id);

	/**
	 * Guardamos un registro en la bbdd<br>
	 * Si el id==-1 creamos y si no modificamos
	 * 
	 * @param pojo
	 * @return
	 */
	public boolean save(P pojo);

	/**
	 * borra registros de la bbdd por su identificador
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(int id);

}
