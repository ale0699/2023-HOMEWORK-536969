package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando {
	
	public ComandoRegala() {
		
		super.setNome("regala");
	}
	
	@Override
	public void esegui(Partita partita) {
		
		if(partita.getStanzaCorrente().getPersonaggio()!=null) {
			
			if(partita.getGiocatore().getBorsa().hasAttrezzo(super.getParametro())) {
				
				Attrezzo attrezzoNellaBorsa = partita.getGiocatore().getBorsa().removeAttrezzo(super.getParametro());
				super.getIO().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzoNellaBorsa, partita));
			}
			else {
				
				super.getIO().mostraMessaggio("L'attrezzo "+ super.getParametro() + " non è presente nella borsa, quindi non può essere regalato");
			}
		}
		else {
			
			super.getIO().mostraMessaggio("Non è presente alcun personaggio nella stanza");
		}
	}

}
