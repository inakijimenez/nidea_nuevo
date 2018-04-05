<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="com.ipartek.formacion.nidea.pojo.Material"%>
<%@page import="java.util.ArrayList"%>

<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp" %>
<%@include file="/templates/alert.jsp" %>



<h1>Materiales</h1>

<ol>
	<c:forEach items="${materiales}" var="material">
		<c:set var="clase" value="" />
		<c:choose>
			
			<c:when test="${material.precio >25}">
				<c:set var="clase" value="text-danger" />
			</c:when>
			<c:when test="${material.precio >6}">
				<c:set var="clase" value="text-primary" />
			</c:when>
			
		</c:choose>
		<li class="${clase}">${material.nombre}- ${material.precio}&euro;</li>
	</c:forEach>
</ol>

${materiales}


<%@include file="/templates/footer.jsp" %>
