package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configurazione;

public class Giocatore {

	//static final private int CFU_INIZIALI = 20;

	private int cfu;
	private Borsa borsa;

	public Giocatore() {

		this.borsa = new Borsa();
		this.cfu = Configurazione.getCFU();
	}

	/**
	 * Restituisce il numero di CFU che ha il giocatore
	 * 
	 * @return numero CFU
	 */
	public int getCfu() {
		return this.cfu;
	}

	/**
	 * Imposta il nuovo numero di CFU del giocatore
	 * 
	 * @param nuovo numero CFU
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	/**
	 * Restituisce la borsa associata al giocatore
	 * 
	 * @return borsa
	 */
	public Borsa getBorsa() {
		return borsa;
	}
	
	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}
}
