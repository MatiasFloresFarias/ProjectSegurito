package controlador.Login;

import java.io.IOException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

import dao.UsuarioDao;
import modelo.Usuario;

/**
 * Servlet implementation class ValidarUsuario
 */
@WebServlet("/ValidarUsuario")
public class ValidarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidarUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		// redireccionar de vuelta al login (lo mismo q hacer el servlet Login Usuario
		// en la practica)
		response.sendRedirect(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);

		String nickname = request.getParameter("txtnickname");
		String password = request.getParameter("txtpassword");
		UsuarioDao userdao = new UsuarioDao();
		Usuario user = new Usuario();
		// validaciones
		String mensaje = "";

		if (nickname.trim().length() == 0 || nickname == null || password.trim().length() == 0 || password == null) {
			mensaje = "Debe ingresar usuario y clave";
		} else {
			user = userdao.obtenerUsuarioByLogin(nickname);
			if (user.getNickname() == "" || user == null) {
				mensaje = "El Usuario ingresado no existe en la base de datos";
			} else {
//					MessageDigest md = MessageDigest.getInstance("MD5");
//					md.update(password.getBytes());
//					byte[] digest = md.digest();

				String myHash = DigestUtils.md5Hex(password).toUpperCase();
//				System.out.println(myHash);
//					String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

				if (myHash.equals(user.getPassword()) == false) {
					mensaje = "La clave ingresada no es correcta";
				} 
//				else {
//					mensaje = "Los datos son correctos";
//
//				}
			}

		}
		
		if (mensaje.trim().length() > 0 || mensaje.trim() != "") {
			request.setAttribute("lmensaje", mensaje);
			request.getRequestDispatcher("loggin.jsp").forward(request, response);
		} else {
			// creacion de sesion
			HttpSession misession = (HttpSession) request.getSession();
			misession.setAttribute("sesionuser", user); 
			response.sendRedirect(request.getContextPath() + "/AsignarRol");
		}
		
	}
	
}
