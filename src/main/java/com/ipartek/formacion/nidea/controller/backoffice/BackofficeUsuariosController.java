package com.ipartek.formacion.nidea.controller.backoffice;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.nidea.pojo.Alert;

/**
 * Servlet implementation class BackOfficeUsuariosController
 */
@WebServlet("/backoffice/usuarios")
public class BackofficeUsuariosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String view = "";
	private Alert alert = new Alert();

	private HashMap<Integer, String> usuarios = new HashMap<>();

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
			// ServletContext context = request.getServletContext();
			// if (context.getAttribute("usuarios_conectados") != null) {
			// usuarios = (HashMap<Integer, String>) context.getAttribute("usuarios_conectados");
			// }
			alert = null;
			view = "usuarios/index.jsp";

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// request.setAttribute("usuarios", usuarios);
			request.setAttribute("alert", alert);
			request.getRequestDispatcher(view).forward(request, response);
		}
	}
}
