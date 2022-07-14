package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DaoUsuarioRepository;


public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DaoUsuarioRepository daoUsuarioRepository = new DaoUsuarioRepository();
	
	
    public ServletUsuarioController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				String idUser = request.getParameter("id");
				daoUsuarioRepository.deletarUser(idUser);
				request.setAttribute("msg", "Excluído com sucesso!");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
					String idUser = request.getParameter("id");
					daoUsuarioRepository.deletarUser(idUser);
					response.getWriter().write("Excluído com sucesso!");					
				} 
			
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {
				String nomeBusca = request.getParameter("nomeBusca");
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioList(nomeBusca);
				System.out.println(dadosJsonUser);
			
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJsonUser);
				response.getWriter().write(json);					
			} 
			
			else {
					request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				}
			
		} catch (Exception e) {
			RequestDispatcher retorno = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			retorno.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String msg = "Operação realizada com sucesso!";
		
		try {
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		ModelLogin modelLogin = new ModelLogin();
		modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
		modelLogin.setNome(nome);
		modelLogin.setEmail(email);
		modelLogin.setLogin(login);
		modelLogin.setSenha(senha);
		
		if(daoUsuarioRepository.validarUsuario(modelLogin.getLogin()) && modelLogin.getId() == null) {
			msg = "Já existe usuário com o mesmo login, informe outro login!";
		} else {
			
		 modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin);
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("modelLogin", modelLogin);
		
		request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		
		}catch (Exception e) {
			RequestDispatcher retorno = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			retorno.forward(request, response);
		
	}

}
}