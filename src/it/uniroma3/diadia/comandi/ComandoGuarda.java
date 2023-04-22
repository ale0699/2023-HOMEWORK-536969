package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {

	private IO io;

	@Override
	public void esegui(Partita partita) {
		
		this.io.mostraMessaggio(partita.getStanzaCorrente().toString());
		this.io.mostraMessaggio("Hai ancora " + partita.getGiocatore().getCfu() + " CFU!");
	}

	@Override
	public void setParametro(String parametro) {
		
	}
	
	@Override
	public String getNome() {
		
		return "guarda";
	}

	@Override
	public String getParametro() {
		
		return null;
	}
	
	@Override
	public void setIO(IO io) {

		this.io = io;
	}
}
