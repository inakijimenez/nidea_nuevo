package com.ipartek.formacion.nidea.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.nidea.model.MaterialDAO;
import com.ipartek.formacion.nidea.model.UsuarioDAO;
import com.ipartek.formacion.nidea.pojo.Alert;
import com.ipartek.formacion.nidea.pojo.Material;
import com.ipartek.formacion.nidea.pojo.Usuario;
import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * Servlet implementation class MaterialesController
 */
@WebServlet("/materiales")
public class MaterialesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String VIEW_INDEX = "/views/materiales/index.jsp";
	private static final String VIEW_FORM = "/views/materiales/form.jsp";
	private static final String VIEW_LOGIN = "/login.jsp";

	public static final int OP_MOSTRAR_FORMULARIO = 1;
	public static final int OP_BUSQUEDA = 2;
	public static final int OP_ELIMINAR = 3;
	public static final int OP_GUARDAR = 4;
	public static final int OP_MOSTRAR_CRUD = 5;

	private String view = "";
	private RequestDispatcher dispatcher;
	private Alert alert;
	private MaterialDAO dao;
	private UsuarioDAO usuarioDao;
	private HttpSession session;

	// Parametros del material
	private int id;
	private String nombre;
	private float precio;
	private Usuario usuario;

	// Parametros comunes
	private String search; // buscador por nombre material
	private int op; // operacion a realizar

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = MaterialDAO.getInstance();
		usuarioDao = UsuarioDAO.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			alert = null;
			recogerParametros(request);

			switch (op) {
			case OP_MOSTRAR_FORMULARIO:
				mostrarFormulario(request);
				break;
			case OP_BUSQUEDA:
				buscar(request);
				break;
			case OP_ELIMINAR:
				eliminar(request);
				break;
			case OP_GUARDAR:
				guardar(request);
				break;
			default:
				listar(request);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			dispatcher = request.getRequestDispatcher(VIEW_INDEX);
			listar(request);
			alert = new Alert();

		} finally {
			request.setAttribute("alert", alert);
			dispatcher.forward(request, response);
		}

	}

	private void mostrarFormulario(HttpServletRequest request) {
		session = request.getSession();
		if (null != session.getAttribute("usuario")) {
			Material material = new Material();
			if (id != -1) {
				material = dao.getById(id);
			}
			request.setAttribute("material", material);
			dispatcher = request.getRequestDispatcher(VIEW_FORM);
		} else {
			dispatcher = request.getRequestDispatcher(VIEW_LOGIN);
			alert = new Alert("Debe estar logeado para crear materiales: ", Alert.TIPO_WARNING);
		}

	}

	private void buscar(HttpServletRequest request) {
		ArrayList<Material> materiales = new ArrayList<Material>();
		materiales = dao.getByName(search);
		request.setAttribute("materiales", materiales);
		dispatcher = request.getRequestDispatcher(VIEW_INDEX);
	}

	private void eliminar(HttpServletRequest request) {
		if (dao.delete(id)) {
			alert = new Alert("Se ha eliminado el registro: " + id, Alert.TIPO_DANGER);
		} else {
			alert = new Alert("Ha habido un error eliminando", Alert.TIPO_WARNING);
		}
		listar(request);
	}

	private void guardar(HttpServletRequest request) {
		session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		Material material = new Material();
		material.setId(id);
		material.setNombre(nombre);
		material.setUsuario(usuario);

		try {
			if (request.getParameter("precio") != null) {
				precio = Float.parseFloat(request.getParameter("precio"));
				material.setPrecio(precio);
			}

			if (material.getPrecio() <= 0) {
				alert = new Alert("El precio debe ser mayor a 0. No se ha guardado el registro", Alert.TIPO_WARNING);

			} else if (material.getNombre().equals("")) {
				alert = new Alert("El nombre no puede estar vacio. No se ha guardado el registro", Alert.TIPO_WARNING);

			} else {
				try {
					if (dao.save(material)) {
						alert = new Alert("Se ha guardado el registro", Alert.TIPO_PRIMARY);
					} else {
						alert = new Alert("Ha habido un error al guardar", Alert.TIPO_DANGER);
					}
				} catch (MySQLIntegrityConstraintViolationException e) {
					alert = new Alert("Material duplicado. No se ha podido crear", Alert.TIPO_WARNING);
					request.setAttribute("material", material);
					// e.printStackTrace();
				} catch (MysqlDataTruncation e) {
					dispatcher = request.getRequestDispatcher(VIEW_FORM);

					alert = new Alert("El nombre solo puede contener 45 caracteres. No se ha guardado el registro",
							Alert.TIPO_WARNING);
				}
			}
		} catch (NumberFormatException e) {

			dispatcher = request.getRequestDispatcher(VIEW_FORM);
			request.setAttribute("material", material);
			alert = new Alert("El precio debe contener solo numeros. No se ha guardado el registro",
					Alert.TIPO_WARNING);

		}

		request.setAttribute("material", material);
		dispatcher = request.getRequestDispatcher(VIEW_FORM);
	}

	private void recogerParametros(HttpServletRequest request) {

		if (request.getParameter("op") != null) {
			op = Integer.parseInt(request.getParameter("op"));
		} else {
			op = 0;
		}

		search = (request.getParameter("search") != null) ? request.getParameter("search") : "";

		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}

		nombre = (request.getParameter("nombre") != null) ? request.getParameter("nombre").trim() : "";
	}

	private void listar(HttpServletRequest request) {
		ArrayList<Material> materiales = new ArrayList<Material>();
		materiales = dao.getAll();

		request.setAttribute("materiales", materiales);
		dispatcher = request.getRequestDispatcher(VIEW_INDEX);
	}

	@Override
	public void destroy() {
		super.destroy();
		dao = null;
		usuarioDao = null;
	}
}
