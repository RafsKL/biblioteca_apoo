package Service;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.time.LocalDate;

import util.Conexao;

public class EmprestimoService {

	 LocalDate dataEmprestimo = LocalDate.now();
	
	 public void Emprestimo(int fk_funcionario_responsavel, int fk_id_usuario, int fk_id_livro, String status) {

		    LocalDate dataEmprestimo = LocalDate.now();
		    LocalDate dataDevolucaoPrevista = dataEmprestimo.plusDays(30); // 30 dias para devolver

		    String sql = "INSERT INTO EMPRESTIMO (fk_funcionario_responsavel, fk_id_usuario, fk_id_livro, data_emprestimo, data_devolucao_prevista, status_emprestimo) VALUES (?, ?, ?, ?, ?, ?::status_emprestimo_enum)";

		    try (Connection conn = Conexao.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {

		        stmt.setInt(1, fk_funcionario_responsavel);
		        stmt.setInt(2, fk_id_usuario);
		        stmt.setInt(3, fk_id_livro);
		        stmt.setDate(4, Date.valueOf(dataEmprestimo));
		        stmt.setDate(5, Date.valueOf(dataDevolucaoPrevista));
		        stmt.setString(6, status.toUpperCase());

		        stmt.executeUpdate();

		        System.out.println("✅ Empréstimo feito com sucesso!");

		    } catch (SQLException e) {
		        System.out.println("❌ Erro ao fazer o empréstimo: " + e.getMessage());
		    }
		}

}
			
			
			
			
		
		
		
		
	
	

