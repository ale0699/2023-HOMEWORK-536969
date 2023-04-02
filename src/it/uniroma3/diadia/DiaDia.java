package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes) & 536969
 * 
 * @version 1.0
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa" };

	private Partita partita;
	private IOConsole ioConsole;

	public DiaDia(IOConsole ioConsole) {
		this.partita = new Partita();
		this.ioConsole = ioConsole;
	}

	public void gioca() {
		String istruzione;

		this.ioConsole.mostraMessaggio(MESSAGGIO_BENVENUTO);

		do
			istruzione = this.ioConsole.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome() == null) {

			this.ioConsole.mostraMessaggio("Nessuna istruzione inserita");
			return false;
		}

		if (this.partita.getCfu() == 0) {

			this.ioConsole.mostraMessaggio("Partita finita, hai terminato i CFU");
			return true;
		}

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			this.ioConsole.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			this.ioConsole.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for (int i = 0; i < elencoComandi.length; i++)
			this.ioConsole.mostraMessaggio(elencoComandi[i] + " ");
		this.ioConsole.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if (direzione == null)
			this.ioConsole.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.ioConsole.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getCfu();
			this.partita.setCfu(cfu - 1);
		}
		this.ioConsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.ioConsole.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
	}

	/**
	 * Comando "Prendi".
	 */
	private void prendi(String nomeAttrezzo) {

		Stanza stanzaCorrente = this.partita.getStanzaCorrente();

		if (stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {

			Attrezzo attrezzoDaPrelevare = stanzaCorrente.getAttrezzo(nomeAttrezzo);
			int pesoRimanenteDellaBorsa = this.partita.getGiocatore().getBorsa().getPesoMax() - this.partita.getGiocatore().getBorsa().getPeso();

			if(attrezzoDaPrelevare.getPeso()<=pesoRimanenteDellaBorsa && this.partita.getGiocatore().getBorsa().getNumeroAttrezzi()<=10) {

				this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrelevare);
				this.partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrelevare.getNome());
				this.ioConsole.mostraMessaggio("Attrezzo " + attrezzoDaPrelevare.getNome() + " preso e messo all'interno della borsa");
			}
			else {

				this.ioConsole.mostraMessaggio("La borsa è piena oppure ha raggiunto il peso massimo");
			}

		} 
		else {

			this.ioConsole.mostraMessaggio("L'attrezzo che hai inserito non è presente nella stanza: "+ this.partita.getStanzaCorrente().getNome());
		}
	}

	/**
	 * Comando "Posa".
	 */
	private void posa(String nomeAttrezzo) {

		Stanza stanzaCorrente = this.partita.getStanzaCorrente();

		if(this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {

			Attrezzo attrezzoDaPrelevare = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			int numeroAttrezziStanzaChePossoAncoraAggiungere = 10 - stanzaCorrente.getNumeroAttrezzi();

			if(numeroAttrezziStanzaChePossoAncoraAggiungere>0) {

				stanzaCorrente.addAttrezzo(attrezzoDaPrelevare);
				this.partita.getGiocatore().getBorsa().removeAttrezzo(attrezzoDaPrelevare.getNome());
				this.ioConsole.mostraMessaggio("Attrezzo " + attrezzoDaPrelevare.getNome() + " rimosso dalla borsa e messo nella stanza: " + stanzaCorrente.getNome());
			}
			else {

				this.ioConsole.mostraMessaggio("La stanza è piena");
			}
		}
		else {

			this.ioConsole.mostraMessaggio("Non è presente nella borsa nessun attrezzo chiamato: " + nomeAttrezzo);
		}
	}

	public static void main(String[] argc) {
		IOConsole ioConsole = new IOConsole();
		DiaDia gioco = new DiaDia(ioConsole);
		gioco.gioca();
	}
}