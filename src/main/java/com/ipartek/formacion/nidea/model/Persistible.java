package com.ipartek.formacion.nidea.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.MySQLDataException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public interface Persistible<P> {

	/**
	 * Lista una tabla de la base de datos ordenado por id descendente y limitado a 500
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
	 * @throws MySQLIntegrityConstraintViolationException
	 * @throws MySQLDataException
	 */
	public boolean save(P pojo) throws SQLException;

	/**
	 * borra registros de la bbdd por su identificador
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(int id);

	/**
	 * Nos mapea un resultado de la bbdd a un pojo
	 * 
	 * @param rs
	 *            ResultSet un registro de la consulta
	 * @return Pojo con los valores de rs o null si no hay valores
	 */
	public P mapper(ResultSet rs) throws SQLException;

}
