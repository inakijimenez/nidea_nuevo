<%@page
	import="com.ipartek.formacion.nidea.controller.backoffice.BackofficeMaterialesController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.ipartek.formacion.nidea.pojo.Usuario"%>
<%@page import="java.util.ArrayList"%>

<%@include file="/templates/head.jsp"%>
<%@include file="/templates/navbar.jsp"%>



<h1>Backoffice Usuarios</h1>
<hr>

<%@include file="/templates/alert.jsp"%>

${usuarios_conectados}
${uPublic}

<table id="table-usuarios" class="display">
	<thead>
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${usuarios_conectados}" var="usuario">

			<tr>
				<td>${usuario.key}</td>
				<td>${usuario.value.nombre}</td>
			</tr>

		</c:forEach>

	</tbody>
</table>

<script type="text/javascript">
	window.history.pushState({}, "hidden", "backoffice/usuarios");
</script>




<%@include file="/templates/footer.jsp"%>
