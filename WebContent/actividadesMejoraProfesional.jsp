<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Registrar Actividades a Mejorar</title>
	<!-- Css de boostrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="https://trentrichardson.com/examples/timepicker/jquery-ui-timepicker-addon.css">
</head>

<body>

	<body>
		<div class="d-sm-flex">
			<div class="card col-sm-4">
				<div class="card-body">
					<form action="CrearActividadesMejora" method="post">
						<div class="form-group">
							<label>Nombre: </label>
							<input type="text" name="txtnombre" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Fecha Inicio: </label>
							<input type="text" name="txtfechainicio" id="datetimepicker" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Fecha Termino: </label>
							<input type="text" name="txtfechatermino" id="datetimepicker2" class="form-control"
								required>
						</div>
						<div class="form-group">
							<label>Estado: </label>
							<input type="text" name="txtestado" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Detalle: </label>
							<input type="text" name="txtdetalle" class="form-control" required>
						</div>

						<div class="form-group">
							<label>Profesional a cargo </label>
							<select name="txtid_profesional" class="form-control form-control">
								<c:forEach items="${listadoprofesionales}" var="profesional">
									<option value="${profesional.getId_profesional()}">${profesional.getNombre()}
										${profesional.getApellido()} </option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label>Cliente</label>
							<select name="txtid_cliente" class="form-control form-control">
								<c:forEach items="${listadoclientes}" var="cliente">
									<option value="${cliente.getId_cliente()}">${cliente.getNombreEmpresa()} </option>
								</c:forEach>
							</select>
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
							<th>Fecha Inicio</th>
							<th>Fecha Termino</th>
							<th>Estado</th>
							<th>Detalle</th>
							<th>Profesional</th>
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
							<td>
								<a class="btn btn-warning"
									href="${pageContext.request.contextPath}/EditarActividadesMejora?id=${actmejora.getIdActMejora()}">Editar</a>
								<a class="btn btn-danger"
									href="${pageContext.request.contextPath}/EliminarActividadesMejora?id=${actmejora.getIdActMejora()}">Eliminar</a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
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
				$('#datetimepicker').datepicker({
					dateFormat: 'dd/mm/yy',

				});
			});
			$(function () {
				$('#datetimepicker2').datepicker({
					dateFormat: 'dd/mm/yy',

				});
			});
		</script>
	</body>

</html>