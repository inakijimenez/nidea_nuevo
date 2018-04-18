package com.ipartek.formacion.nidea.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.nidea.pojo.Usuario;
import com.ipartek.formacion.nidea.util.Utilidades;
import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class UsuarioDAO implements Persistible<Usuario> {

	private static UsuarioDAO INSTANCE = null;

	private UsuarioDAO() {
	}

	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAO();
		}
	}

	public static UsuarioDAO getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	@Override
	public ArrayList<Usuario> getAll() {

		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT u.id as id_usuario, u.nombre as nombre_usuario, u.password, r.id as id_rol, r.nombre as nombre_rol FROM usuario AS u, rol as r WHERE u.id_rol = r.id ORDER BY u.id DESC LIMIT 500;";

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
	}

	@Override
	public Usuario getById(int id) {
		Usuario u = null;
		String sql = "SELECT u.id as id_usuario, u.nombre as nombre_usuario, u.password, r.id as id_rol, r.nombre as nombre_rol FROM usuario AS u, rol as r WHERE u.id = ? ;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					u = mapper(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public boolean save(Usuario pojo) throws MySQLIntegrityConstraintViolationException, MysqlDataTruncation {
		boolean resultado = false;

		if (pojo != null) {
			// Sanitize nombre
			pojo.setNombre(Utilidades.limpiarEspacios(pojo.getNombre()));

			if (pojo.getId() == -1) {
				resultado = crear(pojo);
			} else {
				resultado = modificar(pojo);
			}
		}

		return resultado;
	}

	private boolean modificar(Usuario pojo) throws MySQLIntegrityConstraintViolationException, MysqlDataTruncation {
		boolean resultado = false;

		String sql = "UPDATE `nidea`.`usuario` SET `nombre`=?, `password`=?, `id_rol`=? WHERE  `id`=?;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, pojo.getNombre());
			pst.setString(2, pojo.getPassword());
			pst.setInt(3, pojo.getRol().getId());
			pst.setInt(4, pojo.getId());

			resultado = doSave(pst, pojo);

		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("Nombre duplicado");
			throw e;
		} catch (MysqlDataTruncation e) {
			System.out.println("Nombre muy largo");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	private boolean crear(Usuario pojo) throws MySQLIntegrityConstraintViolationException, MysqlDataTruncation {
		boolean resultado = false;
		String sql = "INSERT INTO `nidea`.`usuario` (`nombre`, `password`, `id_rol`) VALUES (?, ?, ?);";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, pojo.getNombre());
			pst.setString(2, pojo.getPassword());
			pst.setInt(3, pojo.getRol().getId());

			resultado = doSave(pst, pojo);

		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("Nombre duplicado");
			throw e;
		} catch (MysqlDataTruncation e) {
			System.out.println("Nombre muy largo");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	private boolean doSave(PreparedStatement pst, Usuario pojo)
			throws MySQLIntegrityConstraintViolationException, MysqlDataTruncation {
		boolean resultado = false;

		try {
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						pojo.setId(generatedKeys.getInt(1));
					}
				}
				resultado = true;
			}
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("Nombre duplicado");
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
		String sql = "DELETE FROM `nidea`.`usuario` WHERE  `id`=?;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setInt(1, id);

			int affetedRows = pst.executeUpdate();
			if (affetedRows == 1) {
				resultado = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	@Override
	public Usuario mapper(ResultSet rs) throws SQLException {
		Usuario usuario = null;

		if (rs != null) {
			usuario = new Usuario();
			usuario.setId(rs.getInt("id_usuario"));
			usuario.setNombre(rs.getString("nombre_usuario"));
			usuario.setPassword(rs.getString("password"));
			usuario.getRol().setId(rs.getInt("id_rol"));
			usuario.getRol().setNombre(rs.getString("nombre_rol"));

		}

		return usuario;
	}

	public ArrayList<Usuario> getByName(String search) {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT u.id as id_usuario, u.nombre as nombre_usuario, u.password, r.id as id_rol, r.nombre as nombre_rol FROM usuario AS u, rol as r WHERE u.id_rol = r.id AND u.nombre LIKE ? ORDER BY u.id DESC LIMIT 500;";

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

	}

	public Usuario getByExactName(String search) {

		Usuario usuario = new Usuario();
		String sql = "SELECT u.id as id_usuario, u.nombre as nombre_usuario, u.password, r.id as id_rol, r.nombre as nombre_rol FROM usuario AS u, rol as r WHERE u.nombre = ?;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, search);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					usuario = mapper(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	/**
	 * Comprueba nombre y contraseña contra la base de datos
	 * 
	 * @param nombre
	 * @param pass
	 * @return Usuario si se encuentra, Usuario vacio si no
	 */
	public Usuario check(String nombre, String pass) {

		Usuario usuario = new Usuario();
		String sql = "SELECT u.id as id_usuario, u.nombre as nombre_usuario, u.password, r.id as id_rol, r.nombre as nombre_rol FROM usuario AS u, rol as r WHERE u.id_rol = r.id AND u.nombre = ? AND u.password = ?;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, nombre);
			pst.setString(2, pass);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					usuario = mapper(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

}
