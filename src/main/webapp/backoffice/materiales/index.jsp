<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.ipartek.formacion.nidea.pojo.Material"%>
<%@page import="java.util.ArrayList"%>

<%@include file="/templates/head.jsp"%>
<%@include file="/templates/navbar.jsp"%>
<%@include file="/templates/alert.jsp"%>



<h1>Backoffice Materiales</h1>
<hr>


<form action="backoffice/materiales" method="get" class="form-inline">

	<input type="search" name="search" class="form-control mr-sm-2"
		required placeholder="Nombre del material" aria-label="Search">
	<input class="btn btn-primary my-2 my-sm-0" type="submit"
		value="BUSCAR">

</form>

<a href="backoffice/materiales/form.jsp"
	class="btn btn-primary my-2 my-sm-0">Materiales Form</a>

<hr>

<table id="table-materiales" class="display">
	<thead>
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Precio</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${materiales}" var="material">
			<tr>
				<td>${material.id}</td>
				<td>${material.nombre}</td>
				<td>${material.precio}€</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<%-- ${materiales} --%>



<%@include file="/templates/footer.jsp"%>
