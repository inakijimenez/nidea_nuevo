<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="com.ipartek.formacion.nidea.controller.CalculadoraController"%>

<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp" %>
<%@include file="/templates/alert.jsp" %>

<h1>Ongi etorri ${usuario.nombre}</h1>

${applicationScope.usuarios_conectados}

<%@include file="/templates/footer.jsp" %>