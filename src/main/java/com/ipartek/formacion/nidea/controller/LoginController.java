package com.ipartek.formacion.nidea.controller;

import java.io.IOException;
import java.util.ArrayList;

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

	private static final int SESSION_EXPIRATION = -1; // No expira nunca

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			UsuarioDAO dao = UsuarioDAO.getInstance();
			Usuario usuario = new Usuario();

			String nombre = request.getParameter("usuario");
			String password = request.getParameter("password");

			if (nombre != null && password != null && !"".equals(nombre) && !"".equals(password)) {

				usuario = dao.getByExactName(nombre);

				if (usuario.getId() != -1) { // si el nombre de usuario existe
					if (usuario.getPassword().equals(password)) { // se compara con la password y si existe se le asigan
																	// una redireccion basandose en el id_rol
						alert = new Alert("Ongi Etorri", Alert.TIPO_PRIMARY);
						if (usuario.getRol().getId() == 1) {
							view = "backoffice/index.jsp";
						} else if (usuario.getRol().getId() == 2) {
							view = "materiales";
						}
						HttpSession session = request.getSession();
						session.setAttribute("usuario", usuario);
						session.setMaxInactiveInterval(SESSION_EXPIRATION);

					} else {
						alert = new Alert("La contraseña es incorrecta", Alert.TIPO_DANGER);
						view = "login.jsp";
					}
				} else {
					alert = new Alert("El nombre de usuario no existe", Alert.TIPO_DANGER);
					view = "login.jsp";
				}
			} else {
				view = "login.jsp";
				alert = new Alert("Credenciales incorrectas, prueba de nuevo");
			}

		} catch (Exception e) {
			e.printStackTrace();
			view = "login.jsp";
			alert = new Alert();

		} finally {
			request.setAttribute("alert", alert);
			request.getRequestDispatcher(view).forward(request, response);
		}

	}

}
