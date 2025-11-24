package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




import util.Conexao;

public class FuncionarioService {


    public void cadastrarFuncionario(String nome,String cpf,String endereco,String telefone,String email,String cargo,String senha) {
    	   
    	  
        String sql = "INSERT INTO funcionario (nome,cpf,endereco,telefone,email,cargo,senha_hash) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexao.getConnection();
    			PreparedStatement stmt = conn.prepareStatement(sql)){
        	
        stmt.setString(1,nome);
        stmt.setString(2, cpf);
        stmt.setString(3, endereco);
        stmt.setString(4, telefone);
        stmt.setString(5, email);
        stmt.setString(6, cargo);
        stmt.setString(7, senha);
        
        stmt.executeUpdate();
        
        System.out.println("✅ Funcionário cadastrado com sucesso no banco!");
    	
    	} catch (SQLException e) {
            System.out.println("❌ Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }

    	
    

    public boolean loginFuncionario(String CPF, String senha) {
       
    	String sql = "SELECT cpf,senha_hash FROM funcionario WHERE cpf = ? and senha_hash = ? and id_status = 1 ";
    	
    	try (Connection conn = Conexao.getConnection();
    		PreparedStatement stmt = conn.prepareStatement(sql)){
    	
    	stmt.setString(1, CPF);
    	stmt.setString(2, senha);
    	
    	ResultSet rs = stmt.executeQuery();
    	
    	return true;
    	
    	}catch (SQLException e) {
            System.out.println("❌ Erro ao fazer login funcionário: " + e.getMessage());
        }
    	
    	return false;
    	
    }
    	

    public void listarFuncionarios() {
        String sql = "SELECT * FROM funcionario WHERE id_status = 1";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            boolean encontrou = false;

            while (rs.next()) {
                encontrou = true;
                System.out.println("");
                System.out.println("---- Funcionário -----");
                System.out.println("ID: " + rs.getString("id_funcionario"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("CPF: " + rs.getString("cpf"));
                System.out.println("Endereço: " + rs.getString("endereco"));
                System.out.println("Telefone: " + rs.getString("telefone"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Cargo: " + rs.getString("cargo"));
                System.out.println("Senha: " + rs.getString("senha_hash"));
                System.out.println("------------------------");
            }

            if (!encontrou) {
                System.out.println("Nenhum funcionário encontrado no banco.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionários: " + e.getMessage());
        }
    }
    public boolean excluirFuncionario(String CPF) {
        String sql = "UPDATE funcionario SET id_status = 2 WHERE cpf = ?";
        
       try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
    	   
    	   
    	   stmt.setString(1, CPF);
    	   
    	   int linhasAfetadas = stmt.executeUpdate();

           if (linhasAfetadas > 0) {
               System.out.println("✅ Funcionário excluído com sucesso!");
               return true;
           } else {
               System.out.println("❌ Funcionário não encontrado.");
               return false;
           }

       } catch (SQLException e) {
           System.out.println("Erro ao excluir funcionário: " + e.getMessage());
           return false;
       }
   }


    public boolean editarFuncionario(String novoNome, String novoCPF, String novoEndereco, String novoTelefone, String novoEmail, String novoCargo,String novoSenha) {
        String sql = "UPDATE FUNCIONARIO SET nome = ?,endereco = ?, telefone = ?,email = ?,cargo =?,senha_hash=? WHERE cpf = ?";
       
//nome,cpf,endereco,telefone,email,cargo,senha_hash
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setString(2, novoEndereco);
            stmt.setString(3, novoTelefone);
            stmt.setString(4, novoEmail);
            stmt.setString(5, novoCargo);
            stmt.setString(6, novoSenha);
            stmt.setString(7, novoCPF);
            
            

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Funcionario editado com sucesso!");
                return true;
            } else {
                System.out.println("❌ Funcionario não encontrado.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
