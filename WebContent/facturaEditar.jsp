<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Editar Factura</title>
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
				<h1 class="display-4">Editar Factura</h1>
				<form action="EditarFactura" method="post">
					<div class="form-group">
						<label>Fecha Cobro: </label>
						<input type="text" name="txtfechadecobro" id="datetimepicker" class="form-control"
							value="${datosfactura.getFechadecobro()}" required>
					</div>
					<div class="form-group">
						<label>Fecha Vencimiento: </label>
						<input type="text" name="txtfechaVencimiento" id="datetimepicker2" class="form-control"
							value="${datosfactura.getFechaVencimiento()}" required>
					</div>
					<div class="form-group">
						<label>Extras: </label>
						<input type="number" name="txtextras" class="form-control" max="999999999"
							value="${datosfactura.getExtras()}" required>
					</div>
					<div class="form-group">
						<label>Impuestos: </label>
						<input type="number" name="txtimpuestos" class="form-control"
							value="${datosfactura.getImpuestos()}" readonly>
					</div>
					<div class="form-group">
						<label>Subtotal: </label>
						<input type="number" name="txtsubtotal" class="form-control"
							value="${datosfactura.getSubtotal()}" readonly>
					</div>
					<div class="form-group">
						<label>Total: </label>
						<input type="number" name="txttotal" class="form-control" value="${datosfactura.getTotal()}"
							readonly>
					</div>
					<div class="form-group">
						<input type="hidden" name="hdnidfactura" class="form-control"
							value="${datosfactura.getId_factura()}">
					</div>
					<div class="form-group">
						<label>Cliente</label>
						<select name="txtid_cliente" class="form-control form-control">
							<c:forEach items="${listadoclientes}" var="cliente">
								<option value="${cliente.getId_cliente()}"
									${datosfactura.getId_cliente()==cliente.getId_cliente() ? 'selected' : '' }>
									${cliente.getNombreEmpresa()} </option>
							</c:forEach>
						</select>
					</div>
					<input type="submit" name="accion" value="Editar" class="btn btn-info">
					<a class="btn btn-info" href="${pageContext.request.contextPath}/CrearFactura">Cancelar</a>
				</form>
			</div>
			<div class="col align-self-end"></div>
		</div>
	</div>

	<p> Para actualizar el total dirigirse al detalle de la factura
	<p>
		<a class="btn btn-success"
			href="${pageContext.request.contextPath}/CrearDetalleFactura?id=${datosfactura.getId_factura()}">Detalle</a>
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