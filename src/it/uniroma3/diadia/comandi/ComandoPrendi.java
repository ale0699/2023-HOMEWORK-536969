package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {

	private String nomeAttrezzo;
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		
		Stanza stanzaCorrente = partita.getStanzaCorrente();

		if (stanzaCorrente.hasAttrezzo(this.nomeAttrezzo)) {

			Attrezzo attrezzoDaPrelevare = stanzaCorrente.getAttrezzo(this.nomeAttrezzo);
			int pesoRimanenteDellaBorsa = partita.getGiocatore().getBorsa().getPesoMax() - partita.getGiocatore().getBorsa().getPeso();

			if(attrezzoDaPrelevare.getPeso()<=pesoRimanenteDellaBorsa && partita.getGiocatore().getBorsa().getNumeroAttrezzi()<=10) {

				partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrelevare);
				partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrelevare.getNome());
				this.io.mostraMessaggio("Attrezzo " + attrezzoDaPrelevare.getNome() + " preso e messo all'interno della borsa");
			}
			else {

				this.io.mostraMessaggio("La borsa è piena oppure ha raggiunto il peso massimo");
			}

		} 
		else {

			this.io.mostraMessaggio("L'attrezzo che hai inserito non è presente nella stanza: "+ partita.getStanzaCorrente().getNome());
		}
	}

	@Override
	public void setParametro(String parametro) {

		this.nomeAttrezzo = parametro;
	}
	
	@Override
	public String getNome() {
		
		return "prendi";
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
