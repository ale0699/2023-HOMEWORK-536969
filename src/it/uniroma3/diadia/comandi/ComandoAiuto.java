package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	
	public ComandoAiuto() {
		
		super.setNome("aiuto");
	}
	
	static final public String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa", "guarda", "interagisci", "saluta", "regala" };
	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override
	public void esegui(Partita partita) {
		
		
		for (int i = 0; i < elencoComandi.length; i++)
			super.getIO().mostraMessaggio(elencoComandi[i] + " ");
		super.getIO().mostraMessaggio("");
	}
}
