package MainActivities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import InterfacePrincipal.Contato;

public class EdicaodoNome {
	
	public static void main (String[] args) {
		//Chamada da Lista de Contatos
		
		ListaContatos.listarContatos();
		editarNome();
	}
	
	public static void editarNome() {
		
		
		
		//Scanner
		Scanner leia = new Scanner (System.in);
		
		//Abetura de tela principal
		System.out.println("\n============================");
		System.out.println("      EDITAR CONTATO        ");
		System.out.println("Digite o ID do contato que deseja editar: ");
		int idEditar = leia.nextInt();
		
		
		//Chama o método de buscar o contato pelo ID digitado
		Contato contato = buscarContatoPorId(idEditar); 
		
		//Retorna os dados que tem no banco de dados, se ID for número válido
			if(contato != null) {
				System.out.println("\n---------------------------");
				System.out.println(">>> AQUI ESTÁ SEU CONTATO <<<");
				System.out.println("ID: " + contato.getId());
				System.out.println("NOME: " + contato.getNome());
				System.out.println("ENDERECO: " + contato.getEndereco());
				System.out.println("TELEFONE: " + contato.getFone());
				System.out.println("E-MAIL: " + contato.getEmail());
				System.out.println("---------------------------");
			
			
			System.out.println("Agora é sua vez: digite abaixo os novos dados do seu contato!");
			leia.nextLine();
			//Inclusão dos novos dados e salvar no objeto "contato"
			System.out.printf("Novo nome: ");
			String novoNome = leia.nextLine();
			contato.setNome(novoNome);
			
			System.out.printf("Novo endereço: ");
			String novoEndereco = leia.nextLine();
			contato.setEndereco(novoEndereco);
			
			System.out.printf("Novo telefone: ");
			String novoFone = leia.nextLine();
			contato.setFone(novoFone);
			
			System.out.printf("Novo e-mail: ");
			String novoEmail = leia.nextLine();
			contato.setEmail(novoEmail);
			
			//Chamada para atualizar os dados
			atualizarContato(contato);
			
			System.out.println("SUCESSO! O contato foi atualizado!");
			}else {
				System.out.println("Nenhum contato com o ID digitado. Verifique o número digitado");
			}leia.close();
		
	}
	
	//Método que busca o contato pelo ID
	public static Contato buscarContatoPorId(int id) {
		
		Connection c = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:listadecontatos.db");
				
				String sql = "SELECT * FROM PEOPLE WHERE ID = ?";
				stmt = c.prepareStatement(sql);
				stmt.setInt(1, id);
				
				resultSet = stmt.executeQuery();
						
						if(resultSet.next()) {
							Contato contato = new Contato();
							contato.setId(resultSet.getInt("ID"));
							contato.setNome(resultSet.getString("NOME"));
							contato.setEndereco(resultSet.getString("ENDERECO"));
							contato.setFone(resultSet.getString("TELEFONE"));
							contato.setEmail(resultSet.getString("EMAIL"));
							return contato;}
						}
						catch (SQLException | ClassNotFoundException e) {
				            System.err.println("Não foi possível exibir o contato: " + e.getMessage());
				        } finally {
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
			}return null;
		
	}
	
	//Método de substituição do contato
		public static void atualizarContato(Contato contato) {
			Connection c = null;
			PreparedStatement stmt = null;
			
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:listadecontatos.db");
				
				String sql = "UPDATE PEOPLE SET nome = ?, endereco = ?, telefone = ?, email = ? WHERE id = ?";
				stmt = c.prepareStatement(sql);
				
				stmt.setString(1, contato.getNome());
				stmt.setString(2, contato.getEndereco());
				stmt.setString(3, contato.getFone());
				stmt.setString(4, contato.getEmail());
				stmt.setInt(5, contato.getId());
				
				stmt.executeUpdate();
				
				System.out.println("O contato foi alterado com sucesso!");
			}catch(SQLException | ClassNotFoundException e) {
				System.err.println("ERRO: Operação cancelada: " + e.getMessage());
			}finally {
				try {
					if(stmt != null) {
						stmt.close();
					}if(c != null) {
						c.close();
					}}catch(Exception e) {
						e.printStackTrace();
					}
				
				
			}
		}
	}
	



