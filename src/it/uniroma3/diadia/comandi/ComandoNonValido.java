package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {

	public ComandoNonValido() {
		
		super.setNome("Non Valido");
	}
	@Override
	public void esegui(Partita partita) {
		
		super.getIO().mostraMessaggio("Comando non valido!");
	}
}
