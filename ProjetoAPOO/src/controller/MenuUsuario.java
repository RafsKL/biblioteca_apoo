package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Service.UsuarioService;
import util.Conexao;
import Service.LivroService;

public class MenuUsuario {
    private Scanner sc = new Scanner(System.in);
    private UsuarioService usuarioService;
    private LivroService livroService;

    // Guarda o CPF do usuário logado
    private String usuarioLogadoCpf;

    public MenuUsuario(UsuarioService usuarioService, LivroService livroService) {
        this.usuarioService = usuarioService;
        this.livroService = livroService;
    }

    public void exibir() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        if (usuarioService.login(cpf, senha)) {
            usuarioLogadoCpf = cpf; // ✅ guarda o usuário logado
            System.out.println("✅ Login realizado com sucesso!");
            exibirMenu();
        } else {
            System.out.println("❌ CPF ou senha inválidos!");
        }
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Painel do Usuário ---");
            System.out.println("1 - Pesquisar por livro");
            System.out.println("2 - Consultar livros disponíveis");
            System.out.println("3 - Consultar multas");
            System.out.println("4 - Fazer reserva de livro(s)");
            System.out.println("5 - Verificar meus livros");
            System.out.println("6 - Devolver livro");
            System.out.println("7 - Ver meu perfil");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> pesquisarLivro();
                case 2 -> listarLivro();
                case 7 -> verPerfil();
                case 0 -> System.out.println("Saindo do Painel do Usuário...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    public void pesquisarLivro() {
        System.out.print("Digite o ISBN do livro: ");
        String isbn = sc.nextLine();
        System.out.print("Digite o título do livro: ");
        String titulo = sc.nextLine();
        livroService.pesquisarLivro(titulo, isbn);
    }

    public void listarLivro() {
        String sql = "SELECT * FROM livro";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            boolean encontrou = false;

            while (rs.next()) {
                encontrou = true;
                System.out.println("\n------------ Livro ------------");
                System.out.println("ID: " + rs.getString("id_livro"));
                System.out.println("Título: " + rs.getString("titulo"));
                System.out.println("Autor: " + rs.getString("autor"));
                System.out.println("ISBN: " + rs.getString("isbn"));
                System.out.println("Gênero: " + rs.getString("genero"));
            }

            if (!encontrou) {
                System.out.println("Nenhum livro foi encontrado no banco.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }
    }

    // ✅ AGORA ESTE MÉTODO BUSCA OS DADOS DO USUÁRIO LOGADO
    public void verPerfil() {
        if (usuarioLogadoCpf == null) {
            System.out.println("Nenhum usuário logado!");
            return;
        }

        String sql = "SELECT nome, email, cpf, telefone, tipo_usuario FROM usuario WHERE cpf = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuarioLogadoCpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- Meu Perfil ---");
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("E-mail: " + rs.getString("email"));
                System.out.println("CPF: " + rs.getString("cpf"));
                System.out.println("Telefone: " + rs.getString("telefone"));
                System.out.println("Tipo de Usuário: " + rs.getString("tipo_usuario"));
            } else {
                System.out.println("Usuário não encontrado no banco de dados!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar perfil: " + e.getMessage());
        }
    }
}
