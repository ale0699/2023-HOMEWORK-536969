package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {

	public ComandoGuarda() {
		
		super.setNome("guarda");
	}
	
	@Override
	public void esegui(Partita partita) {
		
		super.getIO().mostraMessaggio(partita.getStanzaCorrente().toString());
		super.getIO().mostraMessaggio("Hai ancora " + partita.getGiocatore().getCfu() + " CFU!");
		super.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().toString());;
	}

}
