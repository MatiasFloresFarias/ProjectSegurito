<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Bienvenido</title>
	<link rel="stylesheet" href="style.css">
	<!-- Llamado a boostrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>

<body>

	<div class="container">
		<div class="row">	
			<div class="col align-self-start" ></div>
			<div class="col align-self-center" >

				<c:if test="${lmensaje != null}">
					<c:out value="${lmensaje}" />
				</c:if>
				<form class="form-signin" action="ValidarUsuario" method="post">

					<img class="rounded mx-auto d-block" src="./prevencion.png" alt="Responsive image" width="150"
						height="150">
					<h1 class="text-center">PSegurito</h1>
					<label for="inputEmail" class="sr-only">Nombre de usuario</label>
					<input type="text" name="txtnickname" id="inputEmail" class="form-control" placeholder="Usuario"
						required autofocus>
					<label for="inputPassword" class="sr-only">Password</label>
					<input type="password" name="txtpassword" id="inputPassword" class="form-control"
						placeholder="password" required>
					<div class="checkbox mb-3">
						<label>
							<input type="checkbox" value="remember-me">
							Recordar Cuenta
						</label>
					</div>
					<input class="btn btn-lg btn-primary btn-block" type="submit" value="ingresar"></input>
					<!-- Alerta de claves -->
					<!-- Button trigger modal -->
					<button type="button" class="btn" style="border: none;" data-toggle="modal"
						data-target="#exampleModal">
						Ayuda
					</button>

					<!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Ayuda</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									Registro de claves<br>
									Usuario:cliente -- pass:cliente<br>
									Usuario:profesional -- pass:profesional<br>
									Usuario:administrador -- pass:administrador<br>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary " style="border: none;"
										data-dismiss="modal">Cerrar</button>
								</div>
							</div>
						</div>
					</div>

					<p class="mt-5 mb-3 text-muted">&copy; 2020-PSegurito</p>
				</form>
			</div>
			<div class="col align-self-end"></div>
		</div>
	</div>
</body>


</html>

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