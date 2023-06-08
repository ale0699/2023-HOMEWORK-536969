package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando {
	
	public ComandoPosa() {
		
		super.setNome("posa");
	}
	
	@Override
	public void esegui(Partita partita) {
		
		Stanza stanzaCorrente = partita.getStanzaCorrente();

		if(partita.getGiocatore().getBorsa().hasAttrezzo(super.getParametro())) {

			Attrezzo attrezzoDaPrelevare = partita.getGiocatore().getBorsa().getAttrezzo(super.getParametro());
			int numeroAttrezziStanzaChePossoAncoraAggiungere = Stanza.getNumeroMassimoAttrezzi() - stanzaCorrente.getNumeroAttrezzi();

			if(numeroAttrezziStanzaChePossoAncoraAggiungere>0) {

				stanzaCorrente.addAttrezzo(attrezzoDaPrelevare);
				partita.getGiocatore().getBorsa().removeAttrezzo(attrezzoDaPrelevare.getNome());
				super.getIO().mostraMessaggio("Attrezzo " + attrezzoDaPrelevare.getNome() + " rimosso dalla borsa e messo nella stanza: " + stanzaCorrente.getNome());
			}
			else {

				super.getIO().mostraMessaggio("La stanza è piena");
			}
		}
		else {

			super.getIO().mostraMessaggio("Non è presente nella borsa nessun attrezzo chiamato: " + super.getParametro());
		}
	}

}
