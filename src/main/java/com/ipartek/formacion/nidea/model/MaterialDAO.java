package com.ipartek.formacion.nidea.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.nidea.pojo.Material;
import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class MaterialDAO implements Persistible<Material> {

	private static MaterialDAO INSTANCE = null;

	// No se puede hacer news de esta clase pq se bloquea con este metodo al ser
	// private
	private MaterialDAO() {
	}

	// creador synchronyzed para protegerse de posibles problemas multi-hilo
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MaterialDAO();
		}
	}

	// A este metodo pueden acceder multiples usuarios a la vez y ejecutarlo pero al
	// creador solo uno por estar synchronyzed
	public static MaterialDAO getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	/**
	 * Recupera todos los materiales de la BBDD ordenados por id descendente
	 * 
	 * @return ArrayList<Material> si no existen registros new ArrayList<Material>()
	 */
	@Override
	public ArrayList<Material> getAll() {

		ArrayList<Material> lista = new ArrayList<Material>();
		String sql = "SELECT id, nombre, precio FROM `material` ORDER BY id DESC LIMIT 500;";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				lista.add(mapper(rs));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;

		/*
		 * ArrayList<Material> lista = new ArrayList<Material>(); Connection con = null; PreparedStatement pst = null;
		 * ResultSet rs = null;
		 * 
		 * try {
		 * 
		 * // Class.forName("com.mysql.jdbc.Driver"); // final String URL = //
		 * "jdbc:mysql://192.168.0.42/spoty?user=alumno&password=alumno"; // con = DriverManager.getConnection(URL);
		 * 
		 * con = ConnectionManager.getConnection(); String sql =
		 * "SELECT id, nombre, precio FROM `material` ORDER BY id DESC LIMIT 500;";
		 * 
		 * pst = con.prepareStatement(sql); rs = pst.executeQuery();
		 * 
		 * Material m = null; while (rs.next()) { m = new Material(); m.setId(rs.getInt("id"));
		 * m.setNombre(rs.getString("nombre")); m.setPrecio(rs.getFloat("precio")); lista.add(m); }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } finally {
		 * 
		 * try { if (rs != null) { rs.close(); }
		 * 
		 * if (pst != null) { pst.close(); }
		 * 
		 * if (con != null) { con.close(); } } catch (SQLException e) { e.printStackTrace(); } }
		 * 
		 * return lista;
		 */
	}

	public ArrayList<Material> getByName(String search) {

		ArrayList<Material> lista = new ArrayList<Material>();
		String sql = "SELECT id, nombre, precio FROM material WHERE nombre LIKE ? ORDER BY id DESC LIMIT 500;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, "%" + search + "%");

			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {
					lista.add(mapper(rs));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;

		/*
		 * ArrayList<Material> lista = new ArrayList<Material>(); Connection con = null; PreparedStatement pst = null;
		 * ResultSet rs = null;
		 * 
		 * try {
		 * 
		 * con = ConnectionManager.getConnection(); String sql =
		 * "SELECT id, nombre, precio FROM material WHERE nombre LIKE '%" + search + "%' ORDER BY id DESC LIMIT 500;";
		 * 
		 * pst = con.prepareStatement(sql); rs = pst.executeQuery();
		 * 
		 * Material m = null; while (rs.next()) { m = new Material(); m.setId(rs.getInt("id"));
		 * m.setNombre(rs.getString("nombre")); m.setPrecio(rs.getFloat("precio")); lista.add(m); }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } finally {
		 * 
		 * try { if (rs != null) { rs.close(); }
		 * 
		 * if (pst != null) { pst.close(); }
		 * 
		 * if (con != null) { con.close(); } } catch (SQLException e) { e.printStackTrace(); } }
		 * 
		 * return lista;
		 */
	}

	@Override
	public Material getById(int id) {

		Material m = null;
		String sql = "SELECT id, nombre, precio FROM material WHERE id = ? ;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setInt(1, id);

			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {
					m = mapper(rs);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return m;

	}

	@Override
	public boolean save(Material pojo) throws MySQLIntegrityConstraintViolationException, MysqlDataTruncation {
		boolean resultado = false;

		if (pojo != null) {

			if (pojo.getId() == -1) {
				resultado = crear(pojo);
			} else {
				resultado = modificar(pojo);
			}
		}

		return resultado;
	}

	private boolean modificar(Material pojo) {

		boolean resultado = false;

		String sql = "UPDATE `material` SET `nombre`=?, `precio`=? WHERE  `id`=?;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, pojo.getNombre());
			pst.setFloat(2, pojo.getPrecio());
			pst.setInt(3, pojo.getId());

			int affetedRows = pst.executeUpdate();

			if (affetedRows == 1) {
				resultado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	private boolean crear(Material pojo) throws MySQLIntegrityConstraintViolationException, MysqlDataTruncation {
		boolean resultado = false;
		String sql = "INSERT INTO `material` (`nombre`, `precio`) VALUES (?, ?);";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

			pst.setString(1, pojo.getNombre());
			pst.setFloat(2, pojo.getPrecio());

			int affetedRows = pst.executeUpdate();

			if (affetedRows == 1) {
				try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
					while (generatedKeys.next()) {
						pojo.setId(generatedKeys.getInt(1));
					}
				}
				resultado = true;
			}

		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("Material duplicado");
			throw e;
		} catch (MysqlDataTruncation e) {
			System.out.println("Nombre muy largo");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	@Override
	public boolean delete(int id) {

		boolean resultado = false;
		Connection con = null;
		PreparedStatement pst = null;

		try {

			con = ConnectionManager.getConnection();
			String sql = "DELETE FROM `material` WHERE  `id`= ?;";

			pst = con.prepareStatement(sql);
			pst.setInt(1, id);

			int affetedRows = pst.executeUpdate();

			if (affetedRows == 1) {
				resultado = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {

				if (pst != null) {
					pst.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resultado;
	}

	@Override
	public Material mapper(ResultSet rs) throws SQLException {

		Material m = null;

		if (rs != null) {
			m = new Material();
			m.setId(rs.getInt("id"));
			m.setNombre(rs.getString("nombre"));
			m.setPrecio(rs.getFloat("precio"));
		}

		return m;
	}

}
