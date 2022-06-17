package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

public class ServletsLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletsLogin() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")){
			request.getSession().invalidate();
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
			
		} else {
			doPost(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
	try {
		if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {

		ModelLogin modelLogin = new ModelLogin();
		modelLogin.setLogin(login);
		modelLogin.setSenha(senha);
		if(modelLogin.getLogin().equalsIgnoreCase("admin") && modelLogin.getSenha().equalsIgnoreCase("admin")) {
			
			request.getSession().setAttribute("usuario", modelLogin.getLogin());
			
			if(url == null || url.equals("null")) {
				url = "principal/principal.jsp";
			}
			
			RequestDispatcher retorno = request.getRequestDispatcher(url);
			retorno.forward(request, response);
			
		} else {
			RequestDispatcher retorno = request.getRequestDispatcher("/index.jsp");
			request.setAttribute("msg", "Senha incorreta.");
			retorno.forward(request, response);
		}
		
		} else {
			RequestDispatcher retorno = request.getRequestDispatcher("/index.jsp");
			request.setAttribute("msg", "Por favor, digite a senha.");
			retorno.forward(request, response);
		}
		
	
		
		
	
	} catch (Exception e) {
		RequestDispatcher retorno = request.getRequestDispatcher("/erro.jsp");
		request.setAttribute("msg", e.getMessage());
		retorno.forward(request, response);
	}
	
	}
}