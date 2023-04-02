package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}

	/**
	 * Restituisce false se la somma dei pesi degli attrezzi, all'interno della borsa (compreso quello
	 * che sto provando ad aggiungere), è maggiore del pesoMax della borsa.
	 * Restituisce false se la borsa è piena
	 * Restituisce true se sono riuscito ad aggiungere l'attrezzo
	 * 
	 * @param Attrezzo da aggiungere alla borsa
	 * 
	 * @return false(se borsa piena o troppo pesante), true se ho aggiunto l'attrezzo
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi == 10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}

	/**
	 * Restituisce peso massimoa della borsa
	 * 
	 * @return pesoMassimo borsa
	 */
	public int getPesoMax() {
		return pesoMax;
	}

	/**
	 * Restituisce un oggetto di tipo Attrezzo, trovato all'interno della borsa attraverso il nome
	 * 
	 * @param nomeAttrezzo da cercare
	 * @return Attrezzo cercato
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			if(this.attrezzi[i] != null) {

				if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
					a = attrezzi[i];
			}

		return a;
	}

	/**
	 * Restituisce il peso effettivo della borsa
	 * 
	 * @return peso borsa
	 */
	public int getPeso() {
		int peso = 0;
		for (int i = 0; i < this.numeroAttrezzi; i++) {

			if(this.attrezzi[i]!=null) {

				peso += this.attrezzi[i].getPeso();
			}
		}

		return peso;
	}

	/**
	 * Restituisce true se la borsa è vuota
	 * 
	 * @return true se borsa vuota
	 */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	/**
	 * Restituisce true se la borsa contiene un determinato attrezzo
	 * 
	 * @param nomeAttrezzo da cercare
	 * @return true se l'attrezzo è presente
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	/**
	 * Restituisce un determinato Attrezzo rimosso dalla borsa
	 * 
	 * @param nomeAttrezzo da cercare
	 * @return Attrezzo rimosso dalla borsa
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {

		Attrezzo attrezzo = null;

		if(nomeAttrezzo!=null){

			int i = 0;
			for(Attrezzo a : this.attrezzi) {

				if(a!= null && a.getNome().equals(nomeAttrezzo)) {
					attrezzo = a;
					this.attrezzi[i] = null;
					this.numeroAttrezzi--;
				}

				i++;
			}
		}
		return attrezzo;
	}

	public int getNumeroAttrezzi() {
		return numeroAttrezzi;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			for (int i = 0; i < this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString() + " ");
		} else
			s.append("Borsa vuota");
		return s.toString();
	}
}