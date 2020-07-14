<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Editar Profesional</title>
	<!-- Css de boostrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>

<body>



	<div class="container">
		<div class="row">
			<div class="col align-self-start"></div>
			<div class="col align-self-center">
				<h1 class="display-4">Editar Profesional</h1>

				<form action="EditarProfesional" method="post">
					<div class="form-group">

						<label>Nombre: </label>
						<input type="text" name="txtnombre" class="form-control" value="${datosprofesional.getNombre()}"
							required>
					</div>
					<div class="form-group">
						<label>Apellido: </label>
						<input type="text" name="txtapellido" class="form-control"
							value="${datosprofesional.getApellido()}" required>
					</div>
					<div class="form-group">
						<label>Correo: </label>
						<input type="email" name="txtcorreo" class="form-control"
							value="${datosprofesional.getCorreo()}" required>
					</div>
					<div class="form-group">
						<label>Telefono: </label>
						<input type="text" name="txttelefono" class="form-control"
							value="${datosprofesional.getTelefono()}" required>
					</div>
					<div class="form-group">
						<label>Cargo: </label>
						<input type="text" name="txtcargo" class="form-control" value="${datosprofesional.getCargo()}"
							required>
					</div>
					<div class="form-group">
						<input type="hidden" name="hdnidprofesional" class="form-control"
							value="${datosprofesional.getId_profesional()}">
					</div>

					<input type="submit" name="accion" value="Editar" class="btn btn-info">
					<a class="btn btn-info" href="${pageContext.request.contextPath}/CrearProfesional">Cancelar</a>
				</form>
			</div>
			<div class="col align-self-end"></div>
		</div>

	</div>

	<!-- Jss boostrap -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>

</html>