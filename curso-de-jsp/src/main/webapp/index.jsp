<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

<title>Curso JSP</title>

<style type="text/css">

form {
	position: absolute; 
	top: 40%;
	left: 33%;
	right: 33%
}

h3 {
	position: absolute; 
	top: 30%;
	left: 37%;
}

h5 {
	position: absolute; 
	top: 70%;
	left: 40%;
}

</style>

</head>

<body>
<h3>Bem vindo ao curso de JSP</h3>
<form action="ServletsLogin" method="post" class="row g-3">
<input type="hidden" value="<%= request.getParameter("url") %>" name="url"> <!-- requisitando a url -->

<div class="col-md-6">
<label for="validationCustom01" class="form-label">Usuário</label>
<input type="text" name="login" class="form-control" required="required">
</div>

<div class="col-md-6">
<label for="validationCustom01" class="form-label">Senha</label>
<input type="password" name="senha" class="form-control" required="required">
</div>


<input type="submit" value="Acessar" class="btn btn-primary"><input type="reset" value="Apagar" class="btn btn-primary">

</form>

<h5>${msg}</h5>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"  integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</body>
</html>