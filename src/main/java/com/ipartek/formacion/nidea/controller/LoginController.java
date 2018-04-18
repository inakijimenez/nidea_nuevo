package com.ipartek.formacion.nidea.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.nidea.model.UsuarioDAO;
import com.ipartek.formacion.nidea.pojo.Alert;
import com.ipartek.formacion.nidea.pojo.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String view = "";
	private Alert alert = new Alert();

	private static final String VIEW_BACKOFFICE = "backoffice/index.jsp";
	private static final String VIEW_FRONTOFFICE = "materiales";
	private static final String VIEW_LOGIN = "login.jsp";

	private static final int SESSION_EXPIRATION = -1; // No expira nunca
	private static UsuarioDAO dao;

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);

		dao = UsuarioDAO.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher(VIEW_LOGIN).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Usuario usuario = new Usuario();

			String nombre = request.getParameter("usuario");
			String password = request.getParameter("password");

			if (nombre != null && password != null && !"".equals(nombre) && !"".equals(password)) {

				usuario = dao.check(nombre, password);
				if (usuario.getId() != -1) {

					// usuario = dao.getByExactName(nombre);
					// if (usuario.getId() != -1) { // si el nombre de usuario existe
					// if (usuario.getPassword().equals(password)) { // se compara con la password y si existe se le
					// asigan
					// // una redireccion basandose en el id_rol
					alert = new Alert("Ongi Etorri", Alert.TIPO_PRIMARY);
					if (usuario.getRol().getId() == Usuario.ROL_ADMIN) {
						view = VIEW_BACKOFFICE;
					} else if (usuario.getRol().getId() == Usuario.ROL_USER) {
						view = VIEW_FRONTOFFICE;
					}
					HttpSession session = request.getSession();
					session.setAttribute("usuario", usuario);
					session.setMaxInactiveInterval(SESSION_EXPIRATION);

					// } else {
					// alert = new Alert("La contraseña es incorrecta", Alert.TIPO_DANGER);
					// view = VIEW_LOGIN;
					// }
				} else {
					alert = new Alert("El nombre de usuario no existe", Alert.TIPO_DANGER);
					view = VIEW_LOGIN;
				}
			} else {
				view = VIEW_LOGIN;
				alert = new Alert("Credenciales incorrectas, prueba de nuevo");
			}

		} catch (Exception e) {
			e.printStackTrace();
			view = VIEW_LOGIN;
			alert = new Alert();

		} finally {
			request.setAttribute("alert", alert);
			request.getRequestDispatcher(view).forward(request, response);
		}

	}

}
