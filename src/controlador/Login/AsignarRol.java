package controlador.Login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;

/**
 * Servlet implementation class AsignarRol
 */
@WebServlet("/AsignarRol")
public class AsignarRol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsignarRol() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession misession= (HttpSession) request.getSession(); 
		Usuario suser= (Usuario) misession.getAttribute("sesionuser");
		String rol= suser.getRol();
		if (suser == null || suser.getNickname().trim() == "" ) {
			request.getRequestDispatcher("loggin.jsp").forward(request, response);
		}
		else {
//			UsuarioDao userdao = new UsuarioDao();
//			List<Usuario> lusuarios = new ArrayList<Usuario>();
//			
//			lusuarios = userdao.leerUsuarios();
//			
//			request.setAttribute("listadousuarios", lusuarios);
//			request.getRequestDispatcher("ListadoUsuarios.jsp").forward(request, response);
//			//response.sendRedirect("http://www.google.cl");	
			request.setAttribute("rol", rol);
			request.setAttribute("nickname", suser.getNickname());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
