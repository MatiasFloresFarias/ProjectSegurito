<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Editar Asesoria</title>
	<!-- Css de boostrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="https://trentrichardson.com/examples/timepicker/jquery-ui-timepicker-addon.css">
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col align-self-start"></div>
			<div class="col align-self-center">
				<h1 class="display-4">Editar Asesoria</h1>
				<form action="EditarAsesoria" method="post">
					<div class="form-group">
						<label>Fecha y hora: </label>
						<input type="text" name="txtfechayhora" id="datetimepicker" class="form-control"
							value="${datosasesoria.getFechayhora()}" required>
					</div>
					<div class="form-group">
						<label>Motivo: </label>
						<input type="text" name="txtmotivo" class="form-control" value="${datosasesoria.getMotivo()}"
							required>
					</div>
					<div class="form-group">
						<label>Detalle: </label>
						<input type="text" name="txtdetalle" class="form-control" value="${datosasesoria.getDetalle()}"
							required>
					</div>
					<div class="form-group">
						<input type="hidden" name="hdnidasesoria" class="form-control"
							value="${datosasesoria.getId_asesoria()}">
					</div>

					<div class="form-group">
						<label>Profesional a cargo </label>
						<select name="txt_idprofesional" class="form-control form-control">
							<c:forEach items="${listadoprofesionales}" var="profesional">
								<option value="${profesional.getId_profesional()}"
									${datosasesoria.getId_profesional()==profesional.getId_profesional() ? 'selected'
									: '' }>${profesional.getNombre()} ${profesional.getApellido()}
								</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label>Cliente</label>
						<select name="txt_idcliente" class="form-control form-control">
							<c:forEach items="${listadoclientes}" var="cliente">
								<option value="${cliente.getId_cliente()}"
									${datosasesoria.getId_cliente()==cliente.getId_cliente() ? 'selected' : '' }>
									${cliente.getNombreEmpresa()} </option>
							</c:forEach>
						</select>
					</div>
					<input type="submit" name="accion" value="Editar" class="btn btn-info">
					<a class="btn btn-info" href="${pageContext.request.contextPath}/CrearAsesoria">Cancelar</a>
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
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="https://trentrichardson.com/examples/timepicker/jquery-ui-timepicker-addon.js"></script>
	<script>
		$(function () {
			$('#datetimepicker').datetimepicker({
				dateFormat: 'dd/mm/yy',
				timeFormat: "HH:mm",
			});
		});
	</script>
</body>

</html>