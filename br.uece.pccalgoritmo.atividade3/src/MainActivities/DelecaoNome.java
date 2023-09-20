package MainActivities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DelecaoNome {
	
	public static void main (String[] args) {
		ListaContatos.listarContatos();
		deletarNome();
	}
		
		private static void deletarNome() {
		Scanner leia = new Scanner (System.in);
		
		System.out.println("=============================");
		System.out.println("      DELETAR CONTATO        ");
		System.out.println("\nDigite o nome do contato a ser excluído: ");
		String apagarNome = leia.nextLine();
		
		Connection c = null;
		PreparedStatement stmt = null;
		
			try {
				Class.forName("org.sqlite.JDBC");
	            c = DriverManager.getConnection("jdbc:sqlite:listadecontatos.db");
	            
	            String sql = "DELETE FROM PEOPLE WHERE NOME = ?";
	            stmt = c.prepareStatement(sql);
	            stmt.setString (1, apagarNome);
	            int executar = stmt.executeUpdate();
	            
	            if(executar > 0) {
	            	System.out.println("O contato foi deletado com sucesso! =D");
	            }else {
	            	System.out.println("Não há contatos para deletar");
	            }
			}catch (SQLException | ClassNotFoundException e) {
                System.err.println("Não foi possível realizar a operação: " + e.getMessage());
			}
					finally {
		        try {
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