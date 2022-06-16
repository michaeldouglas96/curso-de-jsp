package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {

	private static String banco = "jdbc:postgresql://localhost:5433/curso-jsp?autoReconnect=true"; /*url unica do db*/
	private static String user = "postgres";
	private static String senha = "admin";
	private static Connection connection;
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		conectar();
	}
	
	public SingleConnectionBanco() {
		conectar(); /**/
	}
	
	private static void conectar() {
		
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver"); /* Carrega o driver de conexão do banco */
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false); /* Pra ñ conectar sem nosso comando */
			} 
			
		} catch (Exception e) {
			e.printStackTrace(); /* mostrar qlqr erro ao conectar*/
			
		}
	}
	
}
