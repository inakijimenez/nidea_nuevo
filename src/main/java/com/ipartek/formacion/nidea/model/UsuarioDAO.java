package com.ipartek.formacion.nidea.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.nidea.pojo.Usuario;
import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class UsuarioDAO implements Persistible<Usuario> {

	private static UsuarioDAO INSTANCE = null;

	// No se puede hacer news de esta clase pq se bloquea con este metodo al ser
	// private
	private UsuarioDAO() {
	}

	// creador synchronyzed para protegerse de posibles problemas multi-hilo
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAO();
		}
	}

	// A este metodo pueden acceder multiples usuarios a la vez y ejecutarlo pero al
	// creador solo uno por estar synchronyzed
	public static UsuarioDAO getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	@Override
	public ArrayList<Usuario> getAll() {

		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT id, nombre, password, id_rol FROM `usuario` ORDER BY id DESC LIMIT 500;";

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
		String sql = "SELECT id, nombre, password, id_rol FROM usuario WHERE id = ? ;";

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario mapper(ResultSet rs) throws SQLException {
		Usuario usuario = null;

		if (rs != null) {
			usuario = new Usuario();
			usuario.setId(rs.getInt("id"));
			usuario.setNombre(rs.getString("nombre"));
			usuario.setPassword(rs.getString("password"));
			usuario.setRol(rs.getInt("id_rol"));
		}

		return usuario;
	}

	public ArrayList<Usuario> getByName(String search) {

		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT id, nombre, id_rol FROM usuario WHERE nombre LIKE ? ORDER BY id DESC LIMIT 500;";

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
		String sql = "SELECT id, nombre, password, id_rol FROM usuario WHERE nombre = ?;";

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

}
