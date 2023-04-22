package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	
	private String nomeAttrezzo;
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		
		Stanza stanzaCorrente = partita.getStanzaCorrente();

		if(partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {

			Attrezzo attrezzoDaPrelevare = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			int numeroAttrezziStanzaChePossoAncoraAggiungere = Stanza.getNumeroMassimoAttrezzi() - stanzaCorrente.getNumeroAttrezzi();

			if(numeroAttrezziStanzaChePossoAncoraAggiungere>0) {

				stanzaCorrente.addAttrezzo(attrezzoDaPrelevare);
				partita.getGiocatore().getBorsa().removeAttrezzo(attrezzoDaPrelevare.getNome());
				this.io.mostraMessaggio("Attrezzo " + attrezzoDaPrelevare.getNome() + " rimosso dalla borsa e messo nella stanza: " + stanzaCorrente.getNome());
			}
			else {

				this.io.mostraMessaggio("La stanza è piena");
			}
		}
		else {

			this.io.mostraMessaggio("Non è presente nella borsa nessun attrezzo chiamato: " + nomeAttrezzo);
		}
	}

	@Override
	public void setParametro(String parametro) {

		this.nomeAttrezzo = parametro;
	}
	
	@Override
	public String getNome() {
		
		return "posa";
	}

	@Override
	public String getParametro() {
		
		return this.nomeAttrezzo;
	}
	
	@Override
	public void setIO(IO io) {

		this.io = io;
	}

}
