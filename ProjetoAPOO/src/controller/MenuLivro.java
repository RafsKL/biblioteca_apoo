package controller;

import java.util.Scanner;

import Service.LivroService;


public class MenuLivro {
	private Scanner sc = new Scanner(System.in);
    private LivroService livroService;
    
    
    
    public MenuLivro(LivroService livroService) {
        this.livroService = livroService;
    }
    
    public void exibir() {
	    int opcao; 
	    do {
	        System.out.println("--- Área dos livros ---");
	        System.out.println("");
	        System.out.println("1 - Consultar livros disponiveis");
	        System.out.println("2 - pesquisar livro");
	        System.out.println("0 - Sair");
	
	        opcao = sc.nextInt();
	        sc.nextLine();
	
	        switch (opcao) {
	            case 1 -> livroService.listarLivro();
	            case 2 ->pesquisarLivro();
	            case 0 -> System.out.println("Saindo da área dos livros...");
	            default -> System.out.println("Opção inválida!");
	        }
	    } while (opcao != 0);
	}
    
    //--------------------Livro------------------------//
    
    public void pesquisarLivro() {
    	System.out.println("Digite o titulo do livro: ");
    	String titulo = sc.nextLine();
    	
    	
    	livroService.pesquisarLivro(titulo);
    }
	
}


    



