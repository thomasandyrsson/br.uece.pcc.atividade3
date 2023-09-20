package MainActivities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListaContatos {
	
	public static void main (String[] args) {
		listarContatos();
	}
		
		public static void listarContatos() {
			
			System.out.println("=======================");
			System.out.println("         CONTATOS      ");
			
			Connection c = null;
			Statement stmt = null;
			ResultSet resultSet = null;		
			
			try {
		        Class.forName("org.sqlite.JDBC");
		        c = DriverManager.getConnection("jdbc:sqlite:listadecontatos.db");
		        
		        stmt = c.createStatement();
		        String sql = "SELECT  * FROM PEOPLE";
		        	        
		        resultSet = stmt.executeQuery(sql);
		        
		        while (resultSet.next()) {
		        	int id = resultSet.getInt("ID");
		        	String nome = resultSet.getString("NOME");
		        	String endereco = resultSet.getString("ENDERECO");
		        	String telefone = resultSet.getString("TELEFONE");
		        	String email = resultSet.getString("EMAIL");
		        
		        	           
		            System.out.printf("\nID: %d", id);
		            System.out.printf("\nNOME: %s", nome);
		            System.out.printf("\nENDERECO: %s", endereco);
		            System.out.printf("\nTELEFONE: %s", telefone);
		            System.out.printf("\nE-MAIL: %s", email);
		            System.out.printf("\n----------------------");
		        }
		        
		        if(resultSet.isBeforeFirst()) {
		        	System.out.println("\nNenhum contato encontrado");
		        }
		        
		        
		    }catch (SQLException | ClassNotFoundException e){
		    	System.err.println("ERRO: A consulta não foi realizada" + e.getMessage());
		    }finally {
		        try {
		            if (resultSet != null) {
		                resultSet.close();
		            }
		            if (stmt != null) {
		                stmt.close();
		            }
		            if (c != null) {
		                c.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		}

		}

			
		}

		
		
		
	