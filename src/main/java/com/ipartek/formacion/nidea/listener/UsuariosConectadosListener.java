package com.ipartek.formacion.nidea.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ipartek.formacion.nidea.pojo.Usuario;

/**
 * Application Lifecycle Listener implementation class UsuariosConectadosListener
 *
 */
@WebListener
public class UsuariosConectadosListener implements HttpSessionAttributeListener, HttpSessionListener {

	HashMap<Integer, Usuario> usuarios = null;
	Usuario usuario = null;

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent se) {

	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub

		ServletContext context = se.getSession().getServletContext();

		if (se.getSession().getAttribute("uPublic") != null) {
			usuario = (Usuario) se.getSession().getAttribute("uPublic");
			if (context.getAttribute("usuarios_conectados") != null) {
				usuarios = (HashMap<Integer, Usuario>) context.getAttribute("usuarios_conectados");

				usuarios.remove(usuario.getId());

				context.setAttribute("usuarios_conectados", usuarios);
			}
		}
	}

	/**
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	@SuppressWarnings("unchecked")
	public void attributeAdded(HttpSessionBindingEvent event) {

		ServletContext context = event.getSession().getServletContext();

		if ("uPublic".equals(event.getName())) {
			if (context.getAttribute("usuarios_conectados") != null) {
				usuarios = (HashMap<Integer, Usuario>) context.getAttribute("usuarios_conectados");
			} else {
				usuarios = new HashMap<Integer, Usuario>();
			}

			usuario = (Usuario) event.getValue();
			usuarios.put(usuario.getId(), usuario);

			context.setAttribute("usuarios_conectados", usuarios);
		}
	}

	/**
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent event) {

	}

	/**
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent event) {

	}

}
