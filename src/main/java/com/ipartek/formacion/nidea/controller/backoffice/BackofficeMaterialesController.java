package com.ipartek.formacion.nidea.controller.backoffice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.nidea.model.MaterialDAO;
import com.ipartek.formacion.nidea.pojo.Alert;
import com.ipartek.formacion.nidea.pojo.Material;

/**
 * Servlet implementation class BackofficeMateriales
 */
@WebServlet("/backoffice/materiales")
public class BackofficeMaterialesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String view = "";
	private Alert alert = null;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Material> materiales = new ArrayList<Material>();

		try {

			String search = request.getParameter("search");
			System.out.println("Filtro de busqueda " + search);

			// enviar como atributo la lista de materiales
			MaterialDAO dao = MaterialDAO.getInstance();

			if (null != search) {
				materiales = dao.findByName(search);
			} else {
				materiales = dao.getAll();
			}

			view = "/backoffice/materiales/index.jsp";

			// request.getRequestDispatcher("/backoffice/materiales/index.jsp").forward(request,
			// response);
		} catch (Exception e) {
			e.printStackTrace();
			view = "login.jsp";
			alert = new Alert();

		} finally {
			request.setAttribute("materiales", materiales);
			request.setAttribute("alert", alert);
			request.getRequestDispatcher(view).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
