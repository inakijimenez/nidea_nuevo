package com.ipartek.formacion.nidea.controller.backoffice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.nidea.model.RolDAO;
import com.ipartek.formacion.nidea.model.UsuarioDAO;
import com.ipartek.formacion.nidea.pojo.Alert;
import com.ipartek.formacion.nidea.pojo.Rol;
import com.ipartek.formacion.nidea.pojo.Usuario;

/**
 * Servlet implementation class BackOfficeUsuariosController
 */
@WebServlet("/backoffice/usuarios")
public class BackofficeUsuariosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String VIEW_FORM = "/backoffice/usuarios/form.jsp";
	private static final String VIEW_INDEX = "/backoffice/usuarios/index.jsp";

	public static final int OP_MOSTRAR_FORMULARIO = 1;
	public static final int OP_BUSQUEDA = 2;
	public static final int OP_ELIMINAR = 3;
	public static final int OP_GUARDAR = 4;

	private RequestDispatcher dispatcher;
	private Alert alert;
	private String view = "";

	private UsuarioDAO dao;
	private RolDAO rolDAO;

	private int id_usuario;
	private String nombre_usuario;
	private Rol rol;

	private String search;
	private int op;

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		dao = UsuarioDAO.getInstance();
		rolDAO = RolDAO.getInstance();
	}

	@Override
	public void destroy() {
		super.destroy();
		dao = null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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

		} catch (

		Exception e) {
			e.printStackTrace();
			dispatcher = request.getRequestDispatcher(VIEW_INDEX);
			listar(request);
			alert = new Alert();

		} finally {
			request.setAttribute("alert", alert);
			dispatcher.forward(request, response);
		}
	}

	private void listar(HttpServletRequest request) {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = dao.getAll();

		request.setAttribute("usuarios", usuarios);
		dispatcher = request.getRequestDispatcher(VIEW_INDEX);

	}

	private void recogerParametros(HttpServletRequest request) {
		if (request.getParameter("op") != null) {
			op = Integer.parseInt(request.getParameter("op"));
		} else {
			op = 0;
		}

		search = (request.getParameter("search") != null) ? request.getParameter("search") : "";

		if (request.getParameter("id") != null) {
			id_usuario = Integer.parseInt(request.getParameter("id"));
		}

		nombre_usuario = (request.getParameter("nombre") != null) ? request.getParameter("nombre").trim() : "";
	}

	private void mostrarFormulario(HttpServletRequest request) {
		Usuario usuario = new Usuario();

		if (id_usuario == -1) {
			request.setAttribute("usuario", usuario);
			dispatcher = request.getRequestDispatcher(VIEW_FORM);
		} else {
			usuario = dao.getById(id_usuario);
			request.setAttribute("usuario", usuario);
			dispatcher = request.getRequestDispatcher(VIEW_FORM);
		}

		ArrayList<Rol> roles = new ArrayList<Rol>();
		roles = rolDAO.getAll();
		request.setAttribute("roles", roles);
	}

	private void guardar(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	private void buscar(HttpServletRequest request) {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = dao.getByName(search);
		request.setAttribute("usuarios", usuarios);
		dispatcher = request.getRequestDispatcher(VIEW_INDEX);
	}

	private void eliminar(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}
}
