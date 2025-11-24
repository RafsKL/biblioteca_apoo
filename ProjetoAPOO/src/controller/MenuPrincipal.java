package controller;

import java.util.Scanner;

import Service.AdminService;
import Service.FuncionarioService;
import Service.LivroService;
import Service.UsuarioService;
import Service.EmprestimoService;

public class MenuPrincipal {
    private Scanner sc = new Scanner(System.in);
    private UsuarioService usuarioService;
    private FuncionarioService funcionarioService;
    private AdminService adminService;
    private LivroService livroService;
    private EmprestimoService emprestimoService;

    public MenuPrincipal(UsuarioService usuarioService, FuncionarioService funcionarioService, AdminService adminService,LivroService livroService,EmprestimoService emprestimoService) {
        this.usuarioService = usuarioService;
        this.funcionarioService = funcionarioService;
        this.adminService = adminService;
        this.livroService = livroService;
        this.emprestimoService = emprestimoService;
    }

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n--- Biblioteca ---");
            System.out.println("");
            System.out.println("1 - Fazer Login (CPF + Senha)"); // Opção de login unificada
            System.out.println("2 - Consultar Livros");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> loginUnificado(); // Chama o novo método de login
                case 2 -> new MenuLivro(livroService).exibir();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void loginUnificado() {
        System.out.println("\n--- Login Unificado ---");
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        // 1. Tentar login como Admin
        // No seu código, o AdminService usa um login de String (Admin) e não CPF, 
        // mas para unificar o sistema, assumirei que o admin usa o CPF "Admin"
        // e a senha "Admin" (conforme sua implementação original no AdminService)
        if (cpf.equalsIgnoreCase("Admin") && adminService.loginAdmin(cpf, senha)) {
            System.out.println("✅ Login bem-sucedido! Acesso de ADMIN.");
            new MenuAdmin(adminService, funcionarioService, usuarioService, livroService, emprestimoService).exibir();
            return;
        }

        // 2. Tentar login como Funcionário
        if (funcionarioService.loginFuncionario(cpf, senha)) {
            System.out.println("✅ Login bem-sucedido! Acesso de FUNCIONÁRIO.");
            new MenuFuncionario(funcionarioService, adminService, usuarioService, livroService).exibir();
            return;
        }

        // 3. Tentar login como Usuário Comum
        if (usuarioService.login(cpf, senha)) {
            System.out.println("✅ Login bem-sucedido! Acesso de USUÁRIO.");
            // Passa o CPF para o MenuUsuario para saber quem está logado
            new MenuUsuario(usuarioService, livroService, cpf).exibirMenu();
            return;
        }

        // Se nenhuma das opções acima funcionou
        System.out.println("❌ Erro de autenticação! CPF ou senha inválidos, ou usuário não cadastrado/inativo.");
    }
}