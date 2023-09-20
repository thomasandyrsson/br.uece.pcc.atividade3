package InterfacePrincipal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class NovaTabela {
	
	public static void main (String[] args) {
		
			Connection c = null;
			Statement stmt = null;
			
			
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:listadecontatos.db");
				System.out.println("Cadastro iniciado");
				
				stmt = c.createStatement();
				String sql = "CREATE TABLE IF NOT EXISTS people" +
                        "(id integer PRIMARY KEY," +
                        "nome text NOT NULL, " + 
                        "endereco text NOT NULL, " + 
                        "telefone text NOT NULL, " + 
                        "email text NOT NULL);"; 
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
			}catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage() );
		         System.exit(0);
			}
			System.out.println("Nome cadastrado com sucesso!");
	}

}
