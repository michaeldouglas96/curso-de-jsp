package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/principal/*"}) /* Interceptar, como se fosse uma porta, antes de encaminhar p página seguinte */
public class FilterAutenticacao extends HttpFilter {
       
  private static Connection connection;
	
	private static final long serialVersionUID = 1L;


	public FilterAutenticacao() {
        super();
        
    }

	
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
		HttpServletRequest req = (HttpServletRequest) request; /* implementando o request p dentro */
		HttpSession session = req.getSession(); /* jogando na sessão */
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		String urlParaAutenticar = req.getServletPath();
		
		if(usuarioLogado == null  && 
				!urlParaAutenticar.contains("principal/ServletsLogin") ) {
			
			RequestDispatcher redireciona = req.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
			request.setAttribute("msg", "Realize o login, por favor!");
			redireciona.forward(request, response);
			return;
			
		} else {
			
			
			chain.doFilter(request, response);
		}
		connection.commit(); /* se deu tudo certo, commita no banco */
		
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				RequestDispatcher retorno = request.getRequestDispatcher("/erro.jsp");
				request.setAttribute("msg", e.getMessage());
				retorno.forward(request, response);
			}
		}
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
		connection = SingleConnectionBanco.getConnection(); /* retornar a conexão */
		
	}

}
