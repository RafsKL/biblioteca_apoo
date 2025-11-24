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
            System.out.println("1 - Área administrativa");
            System.out.println("2 - Login Usuário");
            System.out.println("3 - Livros");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> exibirMenuAdministrativo();
                case 2 -> new MenuUsuario(usuarioService, livroService).exibir();
                case 3 -> new MenuLivro(livroService).exibir();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void exibirMenuAdministrativo() {
        int opcaoAdm;
        do {
            System.out.println("\n--- Área Administrativa ---");
            System.out.println("");
            System.out.println("1 - Login Funcionário");
            System.out.println("2 - Login Admin");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcaoAdm = sc.nextInt();
            sc.nextLine();

            switch (opcaoAdm) {
                case 1 -> {
                    System.out.print("CPF do Funcionário: ");
                    String cpf = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();
 
                    if (funcionarioService.loginFuncionario(cpf, senha)) {
                        System.out.println("✅ Funcionário logado!");
                        new MenuFuncionario(funcionarioService, adminService, usuarioService,livroService).exibir();
                    } else {
                        System.out.println("❌ CPF ou senha inválidos!");
                    }
                }

                case 2 -> {
                    System.out.print("Login: ");
                    String login = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();

                    if (adminService.loginAdmin(login, senha)) {
                        System.out.println("✅ Admin logado!");
                        new MenuAdmin(adminService, funcionarioService, usuarioService, livroService,emprestimoService).exibir();
                    } else {
                        System.out.println("❌ Login ou senha inválidos para o Admin!");
                    }
                }

                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcaoAdm != 0);
    }
}