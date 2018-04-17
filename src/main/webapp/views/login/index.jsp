<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="com.ipartek.formacion.nidea.controller.CalculadoraController"%>

<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp" %>
<%@include file="/templates/alert.jsp" %>

<form action="login-usuario" method="post">

	<div class="form-group">
		<label for="nombre">Nombre</label> 
		<input type="text" class="form-control" name="nombre">		
	</div>
	
	<div class="form-group">
		<label for="id">Id</label> 
		<input type="number" class="form-control" name="id">		
	</div>

	<input type="submit" class="btn btn-block btn-outline-primary" value="Login">
</form>


<%@include file="/templates/footer.jsp" %>