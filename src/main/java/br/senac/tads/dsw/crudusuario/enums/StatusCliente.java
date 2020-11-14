package br.senac.tads.dsw.crudusuario.enums;

public enum StatusCliente {

	ATIVO("Ativo"),
	DESATIVADO("Desativado");
	
	private String descricao;
	
	StatusCliente (String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
