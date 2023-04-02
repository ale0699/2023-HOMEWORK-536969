package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author docente di POO & 536969
 * @see Stanza
 * @version 1.0
 */

public class Partita {

	private Labirinto labirinto;
	private Stanza stanzaCorrente;
	private Giocatore giocatore;

	private boolean finita;

	public Partita() {

		this.labirinto = new Labirinto();
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.giocatore = new Giocatore();

		this.finita = false;

	}

	/**
	 * Restituisce la stanza vincente
	 *
	 * @return viene restituita la stanza vincente.
	 */
	public Stanza getStanzaVincente() {
		return this.labirinto.getStanzaVincente();
	}

	/**
	 * Viene impostata la nuova stanza corrente
	 *
	 * @param StanzaCorrente
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	/**
	 * Restituisce la stanza corrente
	 *
	 * @return viene restituita la stanza corrente.
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * 
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente() == this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * 
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	/**
	 * Restituisce il numero di CFU
	 * 
	 * @return numero CFU associati al giocatore
	 */
	public int getCfu() {
		return this.giocatore.getCfu();
	}

	/**
	 * Viene impostato il nuovo valore dei CFU
	 * 
	 * @param nuovo numero di CFU
	 */
	public void setCfu(int cfu) {
		this.giocatore.setCfu(cfu);
	}

	/**
	 * Restituisce il giocatore associato alla partita
	 * 
	 * @return giocatore
	 */
	public Giocatore getGiocatore() {
		return this.giocatore;
	}

}
