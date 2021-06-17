<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
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
	<h1>Agenda de Contatos</h1>
	<table class="table">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>E-mail</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getId()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getFone()%></td>
				<td><%=lista.get(i).getEmail()%></td>
				<td><a href="select?id=<%=lista.get(i).getId() %>" class="Botao">Editar</a>
				<a href="javascript: confirmar(<%=lista.get(i).getId() %>)" class="excluir">Excluir</a></td>
			</tr>
			<%
			}
			%>
		</tbody>

	</table>
	<a href="novo.html" class="Botao">Novo Contato</a>
	<script src="scripts/confirmacao.js"></script>
</body>
</html>