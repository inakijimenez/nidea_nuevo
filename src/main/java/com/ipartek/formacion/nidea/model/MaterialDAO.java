package com.ipartek.formacion.nidea.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.nidea.pojo.Material;

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
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			// Class.forName("com.mysql.jdbc.Driver");
			// final String URL =
			// "jdbc:mysql://192.168.0.42/spoty?user=alumno&password=alumno";
			// con = DriverManager.getConnection(URL);

			con = ConnectionManager.getConnection();
			String sql = "SELECT id, nombre, precio FROM `material` ORDER BY id DESC LIMIT 500;";

			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			Material m = null;
			while (rs.next()) {
				m = new Material();
				m.setId(rs.getInt("id"));
				m.setNombre(rs.getString("nombre"));
				m.setPrecio(rs.getFloat("precio"));
				lista.add(m);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

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

		return lista;
	}

	public ArrayList<Material> getByName(String search) {

		ArrayList<Material> lista = new ArrayList<Material>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			// Class.forName("com.mysql.jdbc.Driver");
			// final String URL =
			// "jdbc:mysql://192.168.0.42/spoty?user=alumno&password=alumno";
			// con = DriverManager.getConnection(URL);

			con = ConnectionManager.getConnection();
			String sql = "SELECT id, nombre, precio FROM material WHERE nombre LIKE '%" + search + "%' ORDER BY id DESC LIMIT 500;";

			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			Material m = null;
			while (rs.next()) {
				m = new Material();
				m.setId(rs.getInt("id"));
				m.setNombre(rs.getString("nombre"));
				m.setPrecio(rs.getFloat("precio"));
				lista.add(m);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

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

		return lista;
	}

	@Override
	public Material getById(int id) {

		ArrayList<Material> lista = new ArrayList<Material>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		Material m = null;

		try {

			// Class.forName("com.mysql.jdbc.Driver");
			// final String URL =
			// "jdbc:mysql://192.168.0.42/spoty?user=alumno&password=alumno";
			// con = DriverManager.getConnection(URL);

			con = ConnectionManager.getConnection();
			String sql = "SELECT id, nombre, precio FROM material WHERE id=" + id + ";";

			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				m = new Material();
				m.setId(rs.getInt("id"));
				m.setNombre(rs.getString("nombre"));
				m.setPrecio(rs.getFloat("precio"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

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

		return m;
	}

	@Override
	public boolean save(Material pojo) {
		boolean resultado = false;
		Connection con = null;
		PreparedStatement pst = null;

		try {

			con = ConnectionManager.getConnection();
			String sql;

			if (pojo.getId() == -1) {

				sql = "INSERT INTO `material` (`nombre`, `precio`) VALUES (?, ?);";

				pst = con.prepareStatement(sql);
				pst.setString(1, pojo.getNombre());
				pst.setFloat(2, pojo.getPrecio());

			} else {

				sql = "UPDATE `material` SET `nombre`=?, `precio`=? WHERE  `id`=?;";

				pst = con.prepareStatement(sql);
				pst.setString(1, pojo.getNombre());
				pst.setFloat(2, pojo.getPrecio());
				pst.setInt(3, pojo.getId());
			}

			int affetedRows = pst.executeUpdate();

			ResultSet generatedKeys = pst.getGeneratedKeys();
			if (generatedKeys.next()) {
				pojo.setId(generatedKeys.getInt(1));
			}

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

}
