package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import util.Conexao;


public class UsuarioService {


    // CADASTRAR USUÁRIO
    public void cadastrarUsuario(String nome, String email, String senha, String cpf, String telefone, String tipoDeUser) {
    	
    	
        String sql = "INSERT INTO USUARIO (nome, email, senha, cpf, telefone,  tipo_usuario) VALUES (?, ?, ?, ?, ?, ?::tipo_usuario_enum)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

     	
            stmt.setString(1, nome);
            stmt.setString(2, email );
            stmt.setString(3, senha);
            stmt.setString(4, cpf);
            stmt.setString(5, telefone);
            stmt.setString(6, tipoDeUser.toUpperCase()); 
            

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Usuário cadastrado com sucesso!");
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    // LOGIN DO USUÁRIO
    public boolean login(String CPF, String senha) {
        String sql = "SELECT * FROM USUARIO WHERE CPF = ? AND senha = ? AND id_status = 1";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, CPF);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true se encontrou o usuário

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // LISTAR USUÁRIOS
    public void listarUsuarios() {
        String sql = "SELECT * FROM USUARIO WHERE id_status = 1";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            boolean vazio = true;
            while (rs.next()) {
                vazio = false;
                System.out.println("");
                System.out.println("---- Usuário ----");
                System.out.println("ID: " + rs.getString("id_usuario"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("CPF: " + rs.getString("cpf"));
                System.out.println("Telefone: " + rs.getString("telefone"));
                System.out.println("-----------------");
            }

            if (vazio) {
                System.out.println("Nenhum usuário cadastrado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // EXCLUIR USUÁRIO
    public boolean excluirUsuario(String CPF) {
        String sql = "UPDATE usuario SET id_status = 2 WHERE cpf = ?";
        
       try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
    	   
    	   
    	   stmt.setString(1, CPF);
    	   
    	   int linhasAfetadas = stmt.executeUpdate();

           if (linhasAfetadas > 0) {
               System.out.println("✅ Usuario excluído com sucesso!");
               return true;
           } else {
               System.out.println("❌ Usuario não encontrado.");
               return false;
           }

       } catch (SQLException e) {
           System.out.println("Erro ao excluir usuario: " + e.getMessage());
           return false;
       }
   }

    // EDITAR USUÁRIO
    public boolean editarUsuario(String CPF, String novoNome, String novoEmail, String novoSenha, String novoTelefone, String novoTipo_usuario) {
    	 
    	
        String sql = "UPDATE USUARIO SET nome = ?, email = ?, senha = ?, tipo_usuario = ?::tipo_usuario_enum, telefone = ? WHERE cpf = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setString(2, novoEmail);
            stmt.setString(3, novoSenha);
            stmt.setString(4, novoTipo_usuario);
            stmt.setString(5, novoTelefone);
            stmt.setString(6, CPF);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Usuário editado com sucesso!");
                return true;
            } else {
                System.out.println("❌ Usuário não encontrado.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}