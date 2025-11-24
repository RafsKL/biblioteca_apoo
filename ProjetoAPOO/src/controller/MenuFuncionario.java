package controller;

import java.util.Scanner;
import Service.AdminService;
import Service.FuncionarioService;
import Service.UsuarioService;
import Service.LivroService;


public class MenuFuncionario {
    private Scanner sc = new Scanner(System.in);
    private FuncionarioService funcionarioService;
    private AdminService adminService;
    private UsuarioService usuarioService;
    private LivroService livroService;

    public MenuFuncionario(FuncionarioService funcionarioService, AdminService adminService, UsuarioService usuarioService,LivroService livroService) {
        this.funcionarioService = funcionarioService;
        this.adminService = adminService;
        this.usuarioService = usuarioService;
        this.livroService = livroService;
    }

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n--- Painel do Funcionário ---");
            System.out.println("");
            System.out.println("1 - Cadastrar Funcionário");
            System.out.println("2 - Listar Funcionários");
            System.out.println("3 - Editar Funcionário");
            System.out.println("4 - Excluir Funcionário");
            System.out.println("5 - Cadastrar Usuário");
            System.out.println("6 - Listar Usuários");
            System.out.println("7 - Editar Usuário");
            System.out.println("8 - Excluir Usuário");
            System.out.println("9 - Adicionar Livro");
            System.out.println("10 - Excluir Livro");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarFuncionario();
                case 2 -> funcionarioService.listarFuncionarios();
                case 3 -> editarFuncionario();
                case 4 -> excluirFuncionario();
                case 5 -> cadastrarUsuario();
                case 6 -> usuarioService.listarUsuarios();
                case 7 -> editarUsuario();
                case 8 -> excluirUsuario();
                case 9 -> cadastrarLivro();
                case 10 -> excluirLivro();
                case 0 -> System.out.println("Saindo do Painel do Funcionário...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // -------------------- FUNCIONÁRIO --------------------
    private void cadastrarFuncionario() {
        System.out.println("\n--- Cadastro Funcionário ---");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("cpf: ");
        String cpf = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Cargo: ");
        String cargo = sc.nextLine();
        System.out.print("Senha: ");
        String senhahash = sc.nextLine();
        

//String nome,String cpf,String endereco,String telefone,String email,String cargo,
	//	String senha
        funcionarioService.cadastrarFuncionario(nome, cpf, endereco, telefone,email, cargo, senhahash);
    }
    

    private void editarFuncionario() {
        System.out.print("Informe o CPF do funcionário a editar: ");
        String cpf = sc.nextLine();
        System.out.print("Novo nome: ");
        String nome = sc.nextLine();
        System.out.print("Novo Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("Novo telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Novo email: ");
        String email = sc.nextLine();
        System.out.print("Novo cargo: ");
        String cargo = sc.nextLine();
        System.out.print("Novo Senha: ");
        String senha = sc.nextLine();
      //nome,cpf,endereco,telefone,email,cargo,senha_hash
        funcionarioService.editarFuncionario(nome,cpf,endereco,telefone,email,cargo,senha);
    }


    private void excluirFuncionario() {
        System.out.print("Informe o CPF do funcionário a excluir: ");
        String cpf = sc.nextLine();
        funcionarioService.excluirFuncionario(cpf);
    }

    
    // -------------------- USUÁRIO --------------------
    private void cadastrarUsuario() {
        System.out.println("\n--- Cadastro Usuário ---");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Tipo de usuario: ");
        String tipoDeUser = sc.nextLine();
 
        
        usuarioService.cadastrarUsuario(nome, email, senha, cpf, telefone, tipoDeUser);
    }

    private void editarUsuario() {
        System.out.print("Informe o CPF do usuário a editar: ");
        String cpf = sc.nextLine();
        System.out.print("Novo nome: ");
        String nome = sc.nextLine();
        System.out.print("Novo email: ");
        String email = sc.nextLine();
        System.out.print("Novo senha: ");
        String senha = sc.nextLine();
        System.out.print("Novo telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Novo tipo de usuário: ");
        String tipo_usuario = sc.nextLine();

        usuarioService.editarUsuario(cpf, nome, email, senha, telefone, tipo_usuario);
    }

    private void excluirUsuario() {
        System.out.print("Informe o CPF do usuário a excluir: ");
        String cpf = sc.nextLine();
        usuarioService.excluirUsuario(cpf);
    }
    
    
  //-------------------- Livro --------------------
    
    private void cadastrarLivro() {
    	System.out.println("\"\\n--- Cadastro Livro ---\"");
    	System.out.println("Titulo :");
    	String titulo = sc.nextLine();
    	System.out.println("Autor :");
    	String autor = sc.nextLine();
    	System.out.println("Gênero: ");
    	String genero = sc.nextLine();
    	System.out.println("Edição: ");
    	String edicao = sc.nextLine();
    	System.out.println("Sinopse: ");
    	String sinopse = sc.nextLine();
    	System.out.println("Categoria: ");
    	String categoria =sc.nextLine();
    	System.out.println("ISBN: ");
    	String ISBN = sc.nextLine();
    	System.out.println("Ano de publicão: ");
    	int ano_publicacao = sc.nextInt();
    	System.out.println("Quantidade disponivel: ");
    	int quantidade_disponivel = sc.nextInt();
    	
    	livroService.adicionarLivro(titulo, autor, genero, edicao, sinopse, categoria, ISBN, ano_publicacao, quantidade_disponivel);
    }
    
    private void excluirLivro() {
        System.out.print("Informe o CPF do usuário a excluir: ");
        String ISBN = sc.nextLine();
        usuarioService.excluirUsuario(ISBN);
    }
    
}   

	
