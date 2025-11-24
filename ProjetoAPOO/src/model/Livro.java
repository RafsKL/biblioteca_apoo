package model;

public class Livro {
	
	private String titulo;
	private String autor;
	private String genero;
	private String edicao;
	private String sinopse;
	private String disponivel;
	private String ISBN;
	private int ano_publicacao;
	private int quantidade_disponivel;
	
	public Livro() {
		
	}
	
	public Livro(String titulo,String genero) {
		
		
	}
	
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getEdicao() {
		return edicao;
	}
	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public String getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(String disponivel) {
		this.disponivel = disponivel;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public int getAno_publicacao() {
		return ano_publicacao;
	}
	public void setAno_publicacao(int ano_publicacao) {
		this.ano_publicacao = ano_publicacao;
	}
	public int getQuantidade_disponivel() {
		return quantidade_disponivel;
	}
	public void setQuantidade_disponivel(int quantidade_disponivel) {
		this.quantidade_disponivel = quantidade_disponivel;
	}
	
}
