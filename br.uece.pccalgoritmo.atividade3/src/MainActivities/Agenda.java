package MainActivities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import InterfacePrincipal.Contato;

public class Agenda {
	
	public static void main(String[] args) {
		novoContato();
	}


    public static void novoContato() {
        Scanner leia = new Scanner(System.in);
        Contato contato = new Contato();

        System.out.println("Digite o nome do contato:");
        contato.setNome(leia.nextLine());

        System.out.println("Digite agora o endereço:");
        contato.setEndereco(leia.nextLine());

        System.out.println("Digite o telefone:");
        contato.setFone(leia.nextLine());

        System.out.println("Agora digite o e-mail:");
        contato.setEmail(leia.nextLine());
        
        Agenda agenda = new Agenda();
        agenda.incluirContato(contato);
        
    }
        
    
    public void incluirContato (Contato contato) {
    	
    	Scanner leia = new Scanner(System.in);
    	Connection c = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:listadecontatos.db");

            String sql = "INSERT INTO people (nome, endereco, telefone, email) VALUES (?, ?, ?, ?);";
            stmt = c.prepareStatement(sql);

            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEndereco());
            stmt.setString(3, contato.getFone());
            stmt.setString(4, contato.getEmail());

            stmt.executeUpdate();
            System.out.println("Contato inserido com sucesso!");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Não foi possível inserir o contato: " + e.getMessage());
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
        }

        leia.close();
        
    }
    
    	
    
    
}

