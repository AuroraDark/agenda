<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda</title>
<link rel="icon" href="imagens/favicon.svg">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="borda-embaixo">
		<h1 class="titulo">Editar contato</h1>
	</div>
	<form name="frmContato" action="update">
		<table>
			<tr>
				<td><input type="text" name="idcon" id="Caixa3" value="<%out.print(request.getAttribute("idcon"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="Caixa1" value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>

			<tr>
				<td><input type="text" name="fone" class="Caixa2" value="<%out.print(request.getAttribute("fone"));%>"></td>
			</tr>

			<tr>
				<td><input type="text" name="email" class="Caixa1" value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="Botao1" onclick="validar()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>