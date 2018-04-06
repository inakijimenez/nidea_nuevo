<%@page
	import="com.ipartek.formacion.nidea.controller.backoffice.BackofficeMaterialesController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/head.jsp"%>
<%@include file="/templates/navbar.jsp"%>

<a href="backoffice/materiales" class="btn btn-primary">Volver</a>
<hr>

<h1>Materiales Form</h1>

<%@include file="/templates/alert.jsp"%>

<form action="backoffice/materiales" method="get">

	<div class="form-group">
		<label for="id">Id:</label> <input type="number" class="form-control"
			id="id" name="id" placeholder="Id" readonly value="${material.id }">
	</div>
	<div class="form-group">
		<label for="nombre">Nombre Material:</label> <input type="text"
			class="form-control" id="nombre" name="nombre" placeholder="Nombre"
			required value="${material.nombre }">
	</div>
	<div class="form-group">
		<label for="precio">Precio:</label> <input type="number"
			class="form-control" id="precio" name="precio" placeholder="Precio"
			required value="${material.precio }">
	</div>

	<c:if test="${material.id == -1}">
		<input type="hidden" name="op" value="<%=BackofficeMaterialesController.OP_GUARDAR %>">
		<button type="submit" class="btn btn-success btn-block">Crear</button>
	</c:if>

	<c:if test="${material.id != -1}">
		<input type="hidden" name="op" value="<%=BackofficeMaterialesController.OP_GUARDAR %>">
		<button type="submit" class="btn btn-primary btn-block">Modificar</button>
		<a class="btn btn-danger btn-block"
			href="backoffice/materiales?id=${material.id}&op=<%=BackofficeMaterialesController.OP_ELIMINAR %>">Eliminar</a>
	</c:if>
</form>


<%@include file="/templates/footer.jsp"%>