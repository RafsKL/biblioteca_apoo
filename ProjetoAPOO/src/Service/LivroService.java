package Service;
import controller.MenuLivro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import util.Conexao;

public class LivroService {
	
	public void adicionarLivro (String titulo,String autor,String genero,String edicao,
            String sinopse,String categoria,String ISBN,int ano_publicacao,int quantidade_disponivel) {
		

        String sql = "INSERT INTO LIVRO (titulo, autor, genero, edicao, sinopse,categoria, isbn, ano_publicado, quantidade_disponivel) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
      
        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
      
      stmt.setString(1, titulo);
      stmt.setString(2, autor);
      stmt.setString(3, genero);
      stmt.setString(4, edicao);
      stmt.setString(5, sinopse);
      stmt.setString(6, categoria);
      stmt.setString(7, ISBN);
      stmt.setInt(8, ano_publicacao);
      stmt.setInt(9, quantidade_disponivel);
      
      int rowsInserted = stmt.executeUpdate();
      if (rowsInserted > 0) {
          System.out.println("✅ Livro cadastrado com sucesso!");
      }

  } catch (SQLException e) {
      System.err.println("❌ Erro ao cadastrar livro: " + e.getMessage());
  }
}
     
	
	public void listarLivro() {
		
		String sql = "SELECT * FROM livro WHERE id_status = 1";
//titulo, autor, genero, edicao, sinopse,categoria, isbn, ano_publicado, quantidade_disponivel
		
		try (Connection conn = Conexao.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	   
			boolean encontrou = false;
			
			while (rs.next()){
				encontrou = true;
				System.out.println("------------Livro----------------");
				System.out.println("");
				System.out.println("ID: " + rs.getString("id_livro"));
				System.out.println("Titulo :"+ rs.getString("titulo"));
				System.out.println("Autor :"+ rs.getString("autor"));
				System.out.println("ISBN :"+ rs.getString("isbn"));
				System.out.println("genero :"+ rs.getString("genero"));
				System.out.println("");
			
			}
			
			if (!encontrou) {
				System.out.println("Nenhum livro foi encontrado no banco ");
			}
			
			
			
			}catch (SQLException e) {
	            System.out.println("Erro ao listar Livro: " + e.getMessage());
	        }
	    }
	
	 
	public boolean excluirLivro(String ISBN) {
		String sql = "UPDATE LIVRO set id_status = 2 WHERE isbn = ? ";
		
		try (Connection conn = Conexao.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)){
	            	 
	            	 stmt.setString(1, ISBN);
	    
	            	 int linhasAfetadas = stmt.executeUpdate();
	            	 
	            	 if (linhasAfetadas > 0) {
	            		 System.out.println("✅ Livro excluído com sucesso!");
	            		 return true;
	            	 }else {
	            		 System.out.println("❌ Livro não encontrado.");
	            		 return false;
	            	 }
	            	 
	             
	             }catch (SQLException e) {
	 	            System.out.println("Erro ao listar Livro: " + e.getMessage());
	 	            return false;
	 	        }
		
	}
	public void pesquisarLivro(String titulo,String ISBN) {
		//(titulo, autor, genero, edicao, sinopse,categoria, isbn, ano_publicado, quantidade_disponivel
		String sql ="SELECT titulo,genero,edicao,sinopse FROM LIVRO WHERE id_status = 1 AND (titulo LIKE ? OR isbn =?) " ;
		
		try (Connection conn = Conexao.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)){
		
			
			
			stmt.setString(1, "%"+titulo+"%");
			stmt.setString(2, ISBN);
			
			try (ResultSet rs = stmt.executeQuery()) {
				
			
			boolean encontrou = false;
            while (rs.next()) {
                encontrou = true;
                System.out.println("");
                System.out.println("---- Livro -----");
                System.out.println("Titulo: " + rs.getString("titulo"));
                System.out.println("Genero: " + rs.getString("genero"));
                System.out.println("Edição: " + rs.getString("edicao"));
                System.out.println("Sinopse: " + rs.getString("sinopse"));
                System.out.println("------------------------");
                
                
            }

            if (!encontrou) {
                System.out.println("Nenhum Livro encontrado no banco.");
            }
			}

	          
		}catch (SQLException e) {
	            System.out.println("Erro ao encontrar livro: " + e.getMessage());
	            
	}
}
	
}