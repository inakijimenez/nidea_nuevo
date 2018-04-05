package com.ipartek.formacion.nidea.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.nidea.pojo.Alert;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String view = "";
	private Alert alert = new Alert();

	private static final String USER = "admin";
	private static final String PASS = "admin";

	private static final int SESSION_EXPIRATION = -1; // No expira nunca

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			String usuario = request.getParameter("usuario");
			String password = request.getParameter("password");

			if (USER.equalsIgnoreCase(usuario) && PASS.equals(password)) {

				// guardar datos de usuario
				HttpSession session = request.getSession();
				session.setAttribute("usuario", usuario);

				// tiempo de expiracion de session - un valor negativo indica que nunca expira
				/*
				 * tbn se puede configurar en el web.xml <!-- Session Configuration -->
				 * <session-config> <session-timeout>60</session-timeout> </session-config>
				 */
				session.setMaxInactiveInterval(SESSION_EXPIRATION);

				view = "backoffice/index.jsp";
				alert = new Alert("Ongi Etorri", Alert.TIPO_PRIMARY);
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
