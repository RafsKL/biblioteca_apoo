package model;

public class Funcionario extends Usuario{
	
	private String id_funcionario;
	private String cargo;
	
	public Funcionario(String CPF, String senha) {
		super(CPF,senha);
	}

	public String getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(String id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}
