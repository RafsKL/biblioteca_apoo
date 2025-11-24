//Window → Show View → Console


package APOO.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Service.AdminService;
import Service.FuncionarioService;
import Service.UsuarioService;
import Service.LivroService;
import Service.EmprestimoService;
import controller.MenuPrincipal;
import util.Conexao;

public class Main {
    public static void main(String[] args) {
    	
    	System.out.println("Tentando conectar ao banco de dados do Render...");

        try (Connection conexao = Conexao.getConnection()) {
            System.out.println("✅ Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("❌ Falha ao conectar ao banco de dados.");
            e.printStackTrace();
        }
    	

        UsuarioService usuarioService = new UsuarioService();
        FuncionarioService funcionarioService = new FuncionarioService();
        AdminService adminService = new AdminService();
        LivroService livroService = new LivroService();
        EmprestimoService emprestimoService = new EmprestimoService();
        

        MenuPrincipal menu = new MenuPrincipal(usuarioService, funcionarioService, adminService, livroService, emprestimoService);
        menu.exibir();
    }
}
