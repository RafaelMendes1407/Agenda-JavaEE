<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%
JavaBeans contato = (JavaBeans) request.getAttribute("contato");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda de Contatos</title>
<link rel="stylesheet" href="style.css">
<link rel="icon" href="imagens/icon.png">
</head>
<body>
	<h1>Editar Contato</h1>
	<form class="form" name="frmContato" action="editar">
		<input type="text" name="id" value="<%=contato.getId()%>" readonly="readonly"> <input
			type="text" name="nome" value="<%=contato.getNome()%>" > <input
			type="text" name="fone" value="<%=contato.getFone()%>" > <input
			type="text" name="email" value="<%=contato.getEmail()%>" > <input
			class="Botao" type="button" value="Salvar" onclick="validar()">
	</form>

	<script type="text/javascript" src="scripts/validador.js"></script>
</body>
</html>