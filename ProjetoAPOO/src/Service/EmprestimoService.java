package Service;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
	 
	 public void registrarDevolucao(int idEmprestimo) {

		    String sqlSelect = "SELECT data_devolucao_prevista FROM emprestimo WHERE id_emprestimo = ?";
		    String sqlUpdate = "UPDATE emprestimo SET data_devolucao_real = ?, status_emprestimo = 'DEVOLVIDO' WHERE id_emprestimo = ?";
		    String sqlInsertMulta = "INSERT INTO multa (valor, status_pagamento, data_geracao, fk_emprestimo, id_status) VALUES (?, 'PENDENTE', now(), ?, 1)";

		    try (Connection conn = Conexao.getConnection()) {

		        LocalDateTime dataDevolucaoReal = LocalDateTime.now();

		        // Buscar data prevista
		        PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect);
		        stmtSelect.setInt(1, idEmprestimo);
		        var rs = stmtSelect.executeQuery();

		        if (!rs.next()) {
		            System.out.println("❌ Empréstimo não encontrado!");
		            return;
		        }

		        LocalDateTime dataPrevista = rs.getTimestamp("data_devolucao_prevista").toLocalDateTime();

		        long diasAtraso = 0;
		        double valorMulta = 0.0;

		        if (dataDevolucaoReal.isAfter(dataPrevista)) {
		            diasAtraso = java.time.Duration.between(dataPrevista, dataDevolucaoReal).toDays();
		            valorMulta = diasAtraso * 2.50; // R$ 2 por dia
		        }

		        // Atualiza empréstimo
		        PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
		        stmtUpdate.setTimestamp(1, Timestamp.valueOf(dataDevolucaoReal));
		        stmtUpdate.setInt(2, idEmprestimo);
		        stmtUpdate.executeUpdate();

		        // Se tem multa, salva na tabela MULTA
		        if (valorMulta > 0) {
		            PreparedStatement stmtMulta = conn.prepareStatement(sqlInsertMulta);
		            stmtMulta.setDouble(1, valorMulta);
		            stmtMulta.setInt(2, idEmprestimo);
		            stmtMulta.executeUpdate();

		            System.out.println("⚠️ Multa criada: R$ " + valorMulta + " por " + diasAtraso + " dias de atraso.");
		        }

		        System.out.println("📚 Devolução registrada com sucesso!");

		    } catch (SQLException e) {
		        System.out.println("❌ Erro ao registrar devolução: " + e.getMessage());
		    }
		}


}
			
			
			
			
		
		
		
		
	
	

