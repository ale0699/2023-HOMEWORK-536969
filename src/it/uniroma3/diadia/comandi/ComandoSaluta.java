package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando {

	public ComandoSaluta() {
		super.setNome("saluta");
	}
	
	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().getPersonaggio() == null)
			super.getIO().mostraMessaggio("Chi devo salutare? ");
		else 
			super.getIO().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
	}
}
