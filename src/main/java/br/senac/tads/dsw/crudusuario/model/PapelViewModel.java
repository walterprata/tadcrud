package br.senac.tads.dsw.crudusuario.model;

public class PapelViewModel {

	private Papel papel;
	
	private boolean selecionado;

	public PapelViewModel(Papel papel, boolean selecionado) {
		super();
		this.papel = papel;
		this.selecionado = selecionado;
	}

	public PapelViewModel() {
		
	}
	
	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}
	
	
}
