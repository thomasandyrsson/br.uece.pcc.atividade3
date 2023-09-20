package InterfacePrincipal;

import java.util.Scanner;

import MainActivities.Agenda;
import MainActivities.DelecaoNome;
import MainActivities.EdicaodoNome;
import MainActivities.ListaContatos;

public interface Usuario {
	
	public static void main (String[] args) {
			
			Scanner opcao = new Scanner (System.in);
		
		System.out.println("===================================");
		System.out.println("        AGENDA DE CONTATOS         ");
		System.out.println("===================================");
		
		
		System.out.println("\n");
		System.out.println(" (1) LISTAR TODOS OS CONTATOS");
		System.out.println(" (2) BUSCAR UM CONTATO POR NOME");
		System.out.println(" (3) INCLUIR NOVO CONTATO");
		System.out.println(" (4) EDITAR UM CONTATO EXISTENTE");
		System.out.println(" (5) EXCLUIR UM CONTATO");
		System.out.println(" (6) SAIR");
		
		
		System.out.printf("\n>> ESCOLHA UMA OPÇÃO: ");
		
		int escolher = opcao.nextInt();
				
				switch(escolher) {
				case 1:
					System.out.println("\n");
					ListaContatos.listarContatos();
					break;
					
				case 2:
					System.out.println("\n");
					ConsultarDados.novaConsulta();
					break;
					
				case 3:
					System.out.println("\n");
					Agenda.novoContato();
					break;
					
				
				case 4:
					System.out.println("\n");
					EdicaodoNome.main(args);
					break;
					
				case 5:
					System.out.println("\n");
					DelecaoNome.main(args);
					break;
					
				case 6:
					System.out.println("\n");
					System.exit(0);
					
					
				default:
					System.out.println("\n");
					System.out.println("---- OPÇÃO INVÁLIDA ----");
					
				}
		
	}  
	

}
