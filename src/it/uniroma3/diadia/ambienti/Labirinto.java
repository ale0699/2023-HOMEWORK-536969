package it.uniroma3.diadia.ambienti;

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	/**
	 * Restituisce la stanza iniziale del gioco
	 * 
	 * @return Stanza iniziale del gioco
	 */
	public Stanza getStanzaIniziale() {

		return this.stanzaIniziale;
	}

	/**
	 * Restituisce la stanza vincente del gioco
	 * 
	 * @return Stanza vincente del gioco
	 */
	public Stanza getStanzaVincente() {

		return this.stanzaVincente;
	}
}
