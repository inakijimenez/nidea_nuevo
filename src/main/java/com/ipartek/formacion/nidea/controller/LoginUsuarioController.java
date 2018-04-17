package com.ipartek.formacion.nidea.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.nidea.pojo.Alert;
import com.ipartek.formacion.nidea.pojo.Usuario;

/**
 * Servlet implementation class LoginUsuarioController
 */
@WebServlet("/login-usuario")
public class LoginUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String view = "";
	private Alert alert = new Alert();

	private static final int SESSION_EXPIRATION = 500;// 5 segundos

	private Usuario usuario = null;

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
			usuario = recogerDatos(request);

			if (usuario.getId() != -1) {
				// guardar datos de usuario
				HttpSession session = request.getSession();
				session.setAttribute("usuario", usuario);
				session.setAttribute("uPublic", usuario);

				session.setMaxInactiveInterval(SESSION_EXPIRATION);

				view = "views/login/welcome.jsp";
				alert = new Alert("Ongi Etorri", Alert.TIPO_PRIMARY);

			} else {
				view = "views/login/index.jsp";
				alert = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.setAttribute("alert", alert);
			request.getRequestDispatcher(view).forward(request, response);
		}

	}

	private Usuario recogerDatos(HttpServletRequest request) {
		Usuario user = new Usuario();

		if (request.getParameter("nombre") != null && request.getParameter("id") != null
				&& !request.getParameter("nombre").equals("") && !request.getParameter("id").equals("")) {

			user.setNombre(request.getParameter("nombre"));
			user.setId(Integer.parseInt(request.getParameter("id")));
		}

		return user;

	}

}
