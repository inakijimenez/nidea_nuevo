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

import com.ipartek.formacion.nidea.model.MaterialDAO;
import com.ipartek.formacion.nidea.pojo.Alert;
import com.ipartek.formacion.nidea.pojo.Material;
import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * Servlet implementation class BackofficeMateriales
 */
@WebServlet("/backoffice/materiales")
public class BackofficeMaterialesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String VIEW_INDEX = "/backoffice/materiales/index.jsp";
	private static final String VIEW_FORM = "/backoffice/materiales/form.jsp";
	private static final String VIEW_CRUD = "/backoffice/materiales/crud.jsp";

	private RequestDispatcher dispatcher;
	private Alert alert;
	private MaterialDAO dao;

	public static final int OP_MOSTRAR_FORMULARIO = 1;
	public static final int OP_BUSQUEDA = 2;
	public static final int OP_ELIMINAR = 3;
	public static final int OP_GUARDAR = 4;
	public static final int OP_MOSTRAR_CRUD = 5;

	// Parametros del material
	private int id;
	private String nombre;
	private float precio;

	// Parametros comunes
	private String search; // buscador por nombre material
	private int op; // operacion a realizar

	// se ejecuta al iniciar la servlet y solo una vez
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = MaterialDAO.getInstance();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Antes de ejecutar doGet y doPost");
		super.service(req, resp);// llama a doGet o doPost
		System.out.println("Despues de ejecutar doGet y doPost");
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

	/**
	 * Une los metodos doGet y doPost
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
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

			case OP_MOSTRAR_CRUD:
				dispatcher = request.getRequestDispatcher(VIEW_CRUD);
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
			// request.setAttribute("materiales", materiales);
			request.setAttribute("alert", alert);
			dispatcher.forward(request, response);
		}

	}

	private void guardar(HttpServletRequest request) {

		Material material = new Material();
		material.setId(id);
		material.setNombre(nombre);

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

	private void eliminar(HttpServletRequest request) {
		if (dao.delete(id)) {
			alert = new Alert("Se ha eliminado el registro: " + id, Alert.TIPO_DANGER);
		} else {
			alert = new Alert("Ha habido un error eliminando", Alert.TIPO_WARNING);
		}

		listar(request);

	}

	private void buscar(HttpServletRequest request) {
		ArrayList<Material> materiales = new ArrayList<Material>();
		materiales = dao.getByName(search);
		request.setAttribute("materiales", materiales);
		dispatcher = request.getRequestDispatcher(VIEW_INDEX);
	}

	private void listar(HttpServletRequest request) {

		ArrayList<Material> materiales = new ArrayList<Material>();
		materiales = dao.getAll();

		request.setAttribute("materiales", materiales);
		dispatcher = request.getRequestDispatcher(VIEW_INDEX);

	}

	private void mostrarFormulario(HttpServletRequest request) {
		Material material = new Material();

		if (id == -1) {
			request.setAttribute("material", material);
			dispatcher = request.getRequestDispatcher(VIEW_FORM);
		} else {
			material = dao.getById(id);
			request.setAttribute("material", material);
			dispatcher = request.getRequestDispatcher(VIEW_FORM);
		}
	}

	/**
	 * recogemos todos los posibles parametros enviados
	 * 
	 * @param request
	 */
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

	// se ejecuta al parar el servidor de aplicaciones
	@Override
	public void destroy() {
		super.destroy();
		dao = null;
	}

}
