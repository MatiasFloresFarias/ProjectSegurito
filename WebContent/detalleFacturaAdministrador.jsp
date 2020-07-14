<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Registrar Detalle Factura</title>
	<!-- Css de boostrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>

<body>

	<body>
		<div class="d-sm-flex">
			<div class="card col-sm-4">
				<div class="card-body">
					<form action="CrearDetalleFactura" method="post">
						<div class="form-group">
							<label>Nombre: </label>
							<input type="text" name="txtnombre" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Precio: </label>
							<input type="number" name="txtprecio" class="form-control" max="999999999" required>
						</div>
						<div class="form-group">
							<label>Cantidad: </label>
							<input type="number" name="txtcantidad" class="form-control" max="999999999" required>
						</div>
						<div class="form-group">
							<label>Factura: </label>
							<input type="number" name="txtid_factura" class="form-control" value="${facturaid}" checked
								readonly>
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
							<th>Precio</th>
							<th>Cantidad</th>
							<th>Factura</th>
						</tr>
					</thead>

					</tbody>
					</tbody>
					<c:forEach items='${listadodetallefacturas}' var='detallefactura'>
						<tr>
							<td>${detallefactura.getNombre()}</td>
							<td>${detallefactura.getPrecio()}</td>
							<td>${detallefactura.getCantidad()}</td>
							<td>${detallefactura.getId_factura()}</td>
							<td>
								<a class="btn btn-warning"
									href="${pageContext.request.contextPath}/EditarDetalleFactura?id=${detallefactura.getId_detallefactura()}&idfactura=${detallefactura.getId_factura()}">Editar</a>
								<a class="btn btn-danger"
									href="${pageContext.request.contextPath}/EliminarDetalleFactura?id=${detallefactura.getId_detallefactura()}&idfactura=${detallefactura.getId_factura()}">Eliminar</a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<p> Para volver a las facturas
		<p>
			<a class="btn btn-success" href="${pageContext.request.contextPath}/CrearFactura"> Facturas</a>

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