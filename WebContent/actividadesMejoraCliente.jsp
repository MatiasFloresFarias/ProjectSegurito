<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado de actividades de mejora </title>
<!-- Css de boostrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
   <body>

		<div class="col-sm-12">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Fecha Inicio</th>
						<th>Fecha Termino</th>
						<th>Estado</th>
						<th>Detalle</th>
						<th>Profesional a cargo:</th>
						<th>Cliente</th>
					</tr>
				</thead>
				</tbody>
				<c:forEach items='${listadoactmejora}' var='actmejora'>
					<tr>
				<td>${actmejora.getNombre()}</td>
				<td>${actmejora.getFechaInicio()}</td>
				<td>${actmejora.getFechaTermino()}</td>
				<td>${actmejora.getEstado()}</td>
				<td>${actmejora.getDetalle()}</td>
				<td>${actmejora.getProfesional()}</td>
				<td>${actmejora.getCliente()}</td>
					</tr>
					</c:forEach>
				</tbody>			
			</table>
		</div>
	


<!-- Jss boostrap -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>