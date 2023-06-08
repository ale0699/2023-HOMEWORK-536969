package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {

	public ComandoPrendi() {
		
		super.setNome("prendi");
	}
	
	@Override
	public void esegui(Partita partita) {
		
		Stanza stanzaCorrente = partita.getStanzaCorrente();

		if (stanzaCorrente.hasAttrezzo(super.getParametro())) {

			Attrezzo attrezzoDaPrelevare = stanzaCorrente.getAttrezzo(super.getParametro());
			int pesoRimanenteDellaBorsa = partita.getGiocatore().getBorsa().getPesoMax() - partita.getGiocatore().getBorsa().getPeso();

			if(attrezzoDaPrelevare.getPeso()<=pesoRimanenteDellaBorsa ) {

				partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrelevare);
				partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrelevare.getNome());
				super.getIO().mostraMessaggio("Attrezzo " + attrezzoDaPrelevare.getNome() + " preso e messo all'interno della borsa");
			}
			else {

				super.getIO().mostraMessaggio("La borsa ha raggiunto il peso massimo");
			}

		} 
		else {

			super.getIO().mostraMessaggio("L'attrezzo che hai inserito non è presente nella stanza: "+ partita.getStanzaCorrente().getNome());
		}
	}
}
