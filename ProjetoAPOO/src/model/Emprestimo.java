package model;



public class Emprestimo {
	
	private String data_emprestimo;
	private String data_devolução_prevista;
	private String data_devolução_real;
	
	private String status_emprestimo;
	
	private String fk_id_livro;
	private String fk_id_usuario;
	private String fk_funcionario_responsavel;
	
	public Emprestimo() {
		
	}
	
	
	public Emprestimo(String fk_funcionario_responsavel,String fk_id_usuario, String fk_id_livro) {
		
		this.fk_funcionario_responsavel = fk_funcionario_responsavel;
		this.fk_id_livro = fk_id_livro;
		this.fk_id_usuario = fk_id_usuario;
		 
	}


	public String getData_emprestimo() {
		return data_emprestimo;
	}


	public void setData_emprestimo(String data_emprestimo) {
		this.data_emprestimo = data_emprestimo;
	}


	public String getData_devolução_prevista() {
		return data_devolução_prevista;
	}


	public void setData_devolução_prevista(String data_devolução_prevista) {
		this.data_devolução_prevista = data_devolução_prevista;
	}


	public String getData_devolução_real() {
		return data_devolução_real;
	}


	public void setData_devolução_real(String data_devolução_real) {
		this.data_devolução_real = data_devolução_real;
	}


	public String getStatus_emprestimo() {
		return status_emprestimo;
	}


	public void setStatus_emprestimo(String status_emprestimo) {
		this.status_emprestimo = status_emprestimo;
	}


	public String getFk_id_livro() {
		return fk_id_livro;
	}


	public void setFk_id_livro(String fk_id_livro) {
		this.fk_id_livro = fk_id_livro;
	}


	public String getFk_id_usuario() {
		return fk_id_usuario;
	}


	public void setFk_id_usuario(String fk_id_usuario) {
		this.fk_id_usuario = fk_id_usuario;
	}


	public String getFk_funcionario_responsavel() {
		return fk_funcionario_responsavel;
	}


	public void setFk_funcionario_responsavel(String fk_funcionario_responsavel) {
		this.fk_funcionario_responsavel = fk_funcionario_responsavel;
	}
	
	
	
	

}
