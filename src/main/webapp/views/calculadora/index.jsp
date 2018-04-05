<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="com.ipartek.formacion.nidea.controller.CalculadoraController"%>

<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp" %>
<%@include file="/templates/alert.jsp" %>

<form action="calculadora" method="post">

	<div class="form-group">
		<label for="num1">1 Numero</label> 
		<input type="text" class="form-control" name="num1">		
	</div>
	
	<div class="form-group">
		<label for="num2">2 Numero</label> 
		<input type="text" class="form-control" name="num2">		
	</div>

	<select name="operacion">
		<option value="<%=CalculadoraController.OP_SUMAR%>">Sumar</option>
		<option value="<%=CalculadoraController.OP_RESTAR%>">Restar</option>
		<option value="<%=CalculadoraController.OP_MULTIPLICAR%>">Multiplicar</option>
		<option value="<%=CalculadoraController.OP_DIVIDIR%>">Dividir</option>
	</select>


	<input type="submit" class="btn btn-block btn-outline-primary" value="Calcular">
</form>


<%@include file="/templates/footer.jsp" %>
