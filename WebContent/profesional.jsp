<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Registro Profesional</title>
	<!-- Css de boostrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>

<body></body>

<body>
	<div class="d-sm-flex">
		<div class="card col-sm-4">
			<div class="card-body">
				<form action="CrearProfesional" method="post">
					<div class="form-group">
						<label>Nombre:
						</label>
						<input type="text" name="txtNombreProfesional" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Apellido:
						</label>
						<input type="text" name="txtApellido" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Correo:
						</label>
						<input type="email" name="txtCorreo" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Telefono:
						</label>
						<input type="text" name="txtTelefono" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Cargo:
						</label>
						<input type="text" name="txtCargo" class="form-control" required>
					</div>
					<input type="submit" name="accion" value="Agregar" class="btn btn-info">
					<input type="reset" value="Cancelar" class="btn btn-info">
				</form>
			</div>
		</div>
		<div class="col-sm-8">

			<c:if test="${cumensaje != null}">
				<c:out value="${cumensaje}" />
			</c:if>
			<c:if test="${param.emensaje != null}">
				<c:out value="${param.emensaje}" />
			</c:if>

			<table class="table table-striped">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Apellido</th>
						<th>Correo</th>
						<th>Telefono</th>
						<th>Cargo</th>
					</tr>
				</thead>
				</tbody>
				<c:forEach items='${listadoprofesionales}' var='profesional'>
					<tr>
						<td>${profesional.getNombre()}</td>
						<td>${profesional.getApellido()}</td>
						<td>${profesional.getCorreo()}</td>
						<td>${profesional.getTelefono()}</td>
						<td>${profesional.getCargo()}</td>
						<td>
							<a class="btn btn-warning"
								href="${pageContext.request.contextPath}/EditarProfesional?id=${profesional.getId_profesional()}">Editar</a>
							<a class="btn btn-danger"
								href="${pageContext.request.contextPath}/EliminarProfesional?id=${profesional.getId_profesional()}">Eliminar</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
</body>

</html>
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