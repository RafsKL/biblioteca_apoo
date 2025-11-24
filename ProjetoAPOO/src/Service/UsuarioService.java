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
    
    
         
        public void consultarMultasPorCpf(String cpf) {
            // SQL para buscar multas ativas (PENDENTES) de um usuário através do seu CPF
            String sql = "SELECT m.valor, m.status_pagamento, e.data_emprestimo, l.titulo " +
                         "FROM multa m " +
                         "JOIN emprestimo e ON m.fk_emprestimo = e.id_emprestimo " +
                         "JOIN usuario u ON e.fk_id_usuario = u.id_usuario " +
                         "JOIN livro l ON e.fk_id_livro = l.id_livro " +
                         "WHERE u.cpf = ? AND m.status_pagamento = 'PENDENTE'";

            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                // Você precisa obter o ID do usuário usando o CPF para usar em JOINs. 
                // Porém, podemos usar o CPF diretamente se o JOIN for da tabela USUARIO.
                // O código SQL acima está usando JOINs e assume que o USUARIO tem um id_usuario que se liga ao emprestimo.
                // Se o CPF é único, você pode simplificar o JOIN como feito acima (join na tabela U)

                stmt.setString(1, cpf);
                ResultSet rs = stmt.executeQuery();

                boolean encontrou = false;
                while (rs.next()) {
                    encontrou = true;
                    System.out.println("----------------------------------------");
                    System.out.println("Livro: " + rs.getString("titulo"));
                    System.out.println("Valor da Multa: R$ " + String.format("%.2f", rs.getDouble("valor")));
                    System.out.println("Status: " + rs.getString("status_pagamento"));
                    System.out.println("Data Empréstimo: " + rs.getDate("data_emprestimo"));
                }

                if (!encontrou) {
                    System.out.println("✅ Nenhuma multa pendente encontrada para este usuário.");
                }

            } catch (SQLException e) {
                System.out.println("❌ Erro ao consultar multas: " + e.getMessage());
            }
        }
        
        public void listarEmprestimosAtivosPorCpf(String cpf) {
            String sql = "SELECT e.data_emprestimo, e.data_devolucao_prevista, e.status_emprestimo, l.titulo, l.isbn " +
                         "FROM emprestimo e " +
                         "JOIN usuario u ON e.fk_id_usuario = u.id_usuario " +
                         "JOIN livro l ON e.fk_id_livro = l.id_livro " +
                         "WHERE u.cpf = ? AND e.status_emprestimo IN ('ATIVO', 'ATRASADO')";

            try (Connection conn = Conexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, cpf);
                ResultSet rs = stmt.executeQuery();

                boolean encontrou = false;
                while (rs.next()) {
                    encontrou = true;
                    System.out.println("----------------------------------------");
                    System.out.println("Livro: " + rs.getString("titulo") + " (ISBN: " + rs.getString("isbn") + ")");
                    System.out.println("Status: " + rs.getString("status_emprestimo"));
                    System.out.println("Data Empréstimo: " + rs.getDate("data_emprestimo"));
                    System.out.println("Devolução Prevista: " + rs.getDate("data_devolucao_prevista"));
                }

                if (!encontrou) {
                    System.out.println("✅ Nenhuma empréstimo ativo encontrado para este usuário.");
                }

            } catch (SQLException e) {
                System.out.println("❌ Erro ao listar empréstimos ativos: " + e.getMessage());
            }
        }
    
    
}