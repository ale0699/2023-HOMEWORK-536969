package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {
	
	public ComandoFine() {
		
		super.setNome("fine");
	}

	@Override
	public void esegui(Partita partita) {
		
		partita.setFinita();
		super.getIO().mostraMessaggio("Grazie di aver giocato!");// si desidera smettere
	}
}
