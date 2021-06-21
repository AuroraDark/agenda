<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>

<%
@ SuppressWarnings ("unchecked")
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/favicon.svg">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<section class="agenda">
		<div class="borda-embaixo">
			<h1 class="titulo">Agenda de contatos</h1>
		</div>
		<nav class="navprincipal">
		<a href="novo.html" class="Botao1">Novo contato</a>
		<a href="report" class="Botao4">Gerar relatório em pdf</a>
		</nav>
	</section>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>Email</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getIdcon()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getFone()%></td>
				<td><%=lista.get(i).getEmail()%></td>
				<td><nav>
						<a href="select?idcon=<%=lista.get(i).getIdcon()%>"
							class="BotaoAlterar">Editar</a> 
						<a href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)"
							class="BotaoDeletar">Deletar</a>
					</nav></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
<script src="scripts/confirmador.js"></script>
</body>
</html>