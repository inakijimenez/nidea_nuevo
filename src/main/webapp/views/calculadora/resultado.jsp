<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp" %>
<%@include file="/templates/alert.jsp" %>




<%
	float resul = (Float)request.getAttribute("resultado");	
%>		

<h1>Resultado de  la Operación <span class="badge badge-primary"><%=resul%></span> </h1>



<%@include file="/templates/footer.jsp" %>
