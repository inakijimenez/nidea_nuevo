<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/head.jsp"%>
<%@include file="/templates/navbar.jsp"%>
<%@include file="/templates/alert.jsp"%>

<h1>Materiales Form</h1>

<form>
	<div class="form-group">
		<label for="id">Id:</label> <input type="number" class="form-control"
			id="id" name="id" placeholder="Id" disabled>
	</div>
	<div class="form-group">
		<label for="nombre">Nombre Material:</label> <input type="text"
			class="form-control" id="nombre" name="nombre" placeholder="Nombre"
			required>
	</div>
	<div class="form-group">
		<label for="precio">Precio:</label> <input type="number"
			class="form-control" id="precio" name="precio" placeholder="Precio"
			required>
	</div>

	<c:if test="${material.id == -1}">
		<button type="submit" class="btn btn-success btn-block">Crear</button>
	</c:if>

	<c:if test="${material.id != -1}">
		<button type="submit" class="btn btn-primary btn-block">Modificar</button>
		<button type="button" class="btn btn-danger btn-block">Eliminar</button>
	</c:if>
</form>


<%@include file="/templates/footer.jsp"%>