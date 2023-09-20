package InterfacePrincipal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ConsultarDados {
	
	public static void main(String[] args) {
		novaConsulta();
	}
	
	public static void novaConsulta() {
		
	    	
	    	Scanner leia = new Scanner (System.in);
	    	
	    	System.out.println("=========================================");
	    	System.out.println("          CONSULTAR DADOS            \n");
	    	System.out.printf("Digite o nome a ser pesquisado: ");
	    	
	    	String nomeParaConsulta = leia.nextLine();
	    	
	    	Connection c = null;
	        PreparedStatement stmt = null;
	        ResultSet resultSet = null;

	        try {
	            Class.forName("org.sqlite.JDBC");
	            c = DriverManager.getConnection("jdbc:sqlite:listadecontatos.db");
	            
	            String sql = "SELECT  * FROM PEOPLE WHERE NOME LIKE ?";
	            stmt = c.prepareStatement(sql);
	            stmt.setString(1, "%" + nomeParaConsulta + "%");
	            
	            resultSet = stmt.executeQuery();
	            
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
	            
	            if(!resultSet.isBeforeFirst()) {
	            	System.out.println("\nNenhum outro contato semelhante encontrado");
	            }
	            
	            
	        }catch (SQLException | ClassNotFoundException e){
	        	System.err.println("A consulta não foi realizada" + e.getMessage());
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
	        leia.close();
	   }

	}
	
	

