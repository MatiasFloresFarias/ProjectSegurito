<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Registro Cliente</title>
	<!-- Css de boostrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="https://trentrichardson.com/examples/timepicker/jquery-ui-timepicker-addon.css">
</head>

<body>
	<div class="d-sm-flex">
		<div class="card col-sm-4">
			<div class="card-body">
				<form action="CrearCliente" method="post">
					<div class="form-group">
						<label>Nombre de la empresa: </label>
						<input type="text" name="txtNombreEmpresa" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Rut de la empresa: </label>
						<input type="text" name="txtRutEmpresa" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Fecha de registro de la empresa </label>
						<input type="text" name="txtFechaRegistro" id="datetimepicker" class="form-control" required>
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
						<th>Empresa</th>
						<th>Rut</th>
						<th>Fecha de registro</th>
					</tr>
				</thead>

				</tbody>
				</tbody>
				<c:forEach items='${listadoclientes}' var='cliente'>
					<tr>
						<td>${cliente.getNombreEmpresa()}</td>
						<td>${cliente.getRut()}</td>
						<td>${cliente.getFechaRegistro()}</td>
						<td>
							<a class="btn btn-warning"
								href="${pageContext.request.contextPath}/EditarCliente?id=${cliente.getId_cliente()}">Editar</a>
							<a class="btn btn-danger"
								href="${pageContext.request.contextPath}/EliminarCliente?id=${cliente.getId_cliente()}">Eliminar</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<!-- Js boostrap -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="https://trentrichardson.com/examples/timepicker/jquery-ui-timepicker-addon.js"></script>
	<script>
		$(function () {
			$('#datetimepicker').datepicker({
				dateFormat: 'dd/mm/yy',
			});
		});
	</script>
</body>

</html>