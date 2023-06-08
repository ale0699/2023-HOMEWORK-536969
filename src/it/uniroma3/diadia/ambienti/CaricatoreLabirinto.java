package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";

	/*
	 * prefisso della riga contenente le specifiche degli attrezzi da collocare nel
	 * formato <nomeAttrezzo> <peso> <nomeStanza>
	 */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/*
	 * prefisso della riga contenente le specifiche dei collegamenti tra stanza nel
	 * formato <nomeStanzaDa> <direzione> <nomeStanzaA>
	 */
	private static final String USCITE_MARKER = "Uscite:";

	/*
	 * prefisso della riga contenente le specifiche di aggiunta di un nuovo
	 * personaggio Strega nel formato <nomeStanza> <nomeStrega> <presentazione>
	 */
	private static final String STREGA_MARKER = "Strega:";

	/*
	 * prefisso della riga contenente le specifiche di aggiunta di un nuovo
	 * personaggio Cane nel formato <nomeStanza> <nomeCane> <presentazione>
	 * <ciboPreferito> <attrezzo> <pesoAttrezzo>
	 */
	private static final String CANE_MARKER = "Cane:";

	/*
	 * prefisso della riga contenente le specifiche di aggiunta di un nuovo
	 * personaggio Mago nel formato <nomeStanza> <nomeMago> <presentazione>
	 * <attrezzo>
	 */
	private static final String MAGO_MARKER = "Mago:";

	/*
	 * prefisso della riga contenente le specifiche di aggiunta di una stanza buia
	 * <nomeStanza> <attrezzoCheIllumina>
	 */
	private static final String STANZA_BUIA_MARKER = "Buia:";

	/*
	 * prefisso della riga contenente le specifiche di aggiunta di una stanza magica
	 * <nomeStanza>
	 */
	private static final String STANZA_MAGICA_MARKER = "Magica:";

	/*
	 * prefisso della riga contenente le specifiche di aggiunta di una stanza
	 * bloccata <nomeStanza> <direzione> <attrezzoCheSblocca>
	 */
	private static final String STANZA_BLOCCATA_MARKER = "Bloccata:";

	/*
	 * Esempio di un possibile file di specifica di un labirinto (vedi
	 * POO-26-eccezioni-file.pdf)
	 * 
	 * Stanze: biblioteca, N10, N11 
	 * Inizio: N10 
	 * Vincente: N11 
	 * Attrezzi: martello 10 biblioteca, pinza 2 N10 
	 * Uscite: biblioteca nord N10, biblioteca sud N11 
	 * Buia: N14 lanterna, N18 torcia 
	 * Bloccata: N20 nord chiave 
	 * Magica: N16 
	 * Strega: N10 Strega sonoLaStrega 
	 * Cane: 
	 * Mago:
	 * 
	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String, Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}
	
	public CaricatoreLabirinto(StringReader stringa) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String, Stanza>();
		this.reader = new LineNumberReader(stringa);
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStreghe();
			this.leggiECreaCani();
			this.leggiECreaMaghi();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker), "era attesa una riga che cominciasse per " + marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for (String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String stanzeBloccate = this.leggiRigaCheCominciaPer(STANZA_BLOCCATA_MARKER);

		for (String stanzaBloccata : separaStringheAlleVirgole(stanzeBloccate)) {

			try (Scanner scannerDiLinee = new Scanner(stanzaBloccata)) {
				check(scannerDiLinee.hasNext(),
						msgTerminazionePrecoce("Non è presente il nome della stanza" + stanzaBloccata));
				String nomeStanza = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(),
						msgTerminazionePrecoce("Non è presente la direzione in cui è bloccata la stanza ")
								+ stanzaBloccata);
				String nomeDirezione = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(),
						msgTerminazionePrecoce("Non è presente il nome dell'attrezzo per sbloccare la stanza ")
								+ stanzaBloccata);
				String nomeAttrezzo = scannerDiLinee.next();
				StanzaBloccata stanza = new StanzaBloccata(nomeStanza, nomeDirezione, nomeAttrezzo);
				this.nome2stanza.put(nomeStanza, stanza);
			}

		}
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String stanzeBuie = this.leggiRigaCheCominciaPer(STANZA_BUIA_MARKER);

		for (String stanzaBuia : separaStringheAlleVirgole(stanzeBuie)) {

			try (Scanner scannerDiLinee = new Scanner(stanzaBuia)) {
				
				check(scannerDiLinee.hasNext(),
						msgTerminazionePrecoce("Non è presente il nome della stanza" + stanzaBuia));
				String nomeStanza = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(),
						msgTerminazionePrecoce("Non è presente il nome dell'attrezzo per vedere attraverso il buio ")
								+ stanzaBuia);
				String nomeAttrezzo = scannerDiLinee.next();
				StanzaBuia stanza = new StanzaBuia(nomeStanza, nomeAttrezzo);
				this.nome2stanza.put(nomeStanza, stanza);
			}

		}
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String stanzeMagiche = this.leggiRigaCheCominciaPer(STANZA_MAGICA_MARKER);

		for (String stanzaMagica : separaStringheAlleVirgole(stanzeMagiche)) {

			StanzaMagica stanza = new StanzaMagica(stanzaMagica);
			this.nome2stanza.put(stanzaMagica, stanza);
		}
	}

	private void leggiECreaStreghe() throws FormatoFileNonValidoException {
		String streghe = this.leggiRigaCheCominciaPer(STREGA_MARKER);

		for (String strega : separaStringheAlleVirgole(streghe)) {

			try (Scanner scannerDiLinee = new Scanner(strega)) {

				check(scannerDiLinee.hasNext(),
						msgTerminazionePrecoce("Non è presente il nome della stanza" + strega));
				String nomeStanza = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(),
						msgTerminazionePrecoce("Non è presente il nome della strega" + strega));
				String nomeStrega = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("Non è presente la presentazione ") + strega);
				String presentazione = scannerDiLinee.next();
				AbstractPersonaggio nuovaStrega = new Strega(nomeStrega, presentazione);
				this.nome2stanza.get(nomeStanza).setPersonaggio(nuovaStrega);
			}

		}
	}

	private void leggiECreaCani() throws FormatoFileNonValidoException {
		String cani = this.leggiRigaCheCominciaPer(CANE_MARKER);

		for (String cane : separaStringheAlleVirgole(cani)) {

			try (Scanner scannerDiLinee = new Scanner(cane)) {

				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("Non è presente il nome della stanza" + cane));
				String nomeStanza = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("Non è presente il nome del cane" + cane));
				String nomeCane = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("Non è presente la presentazione ") + cane);
				String presentazione = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(),
						msgTerminazionePrecoce("Non è presente il nome del cibo preferito ") + cane);
				String ciboPreferito = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(),
						msgTerminazionePrecoce("Non è presente il nome dell'attrezzo ") + cane);
				String attrezzoCane = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("Non è presente peso dell'attrezzo ") + cane);
				Integer pesoAttrezzo = Integer.parseInt(scannerDiLinee.next());
				
				
				AbstractPersonaggio nuovoCane = new Cane(nomeCane, presentazione, ciboPreferito, new Attrezzo(attrezzoCane, pesoAttrezzo));
				this.nome2stanza.get(nomeStanza).setPersonaggio(nuovoCane);
			}
		}
	}

	private void leggiECreaMaghi() throws FormatoFileNonValidoException {
		String maghi = this.leggiRigaCheCominciaPer(MAGO_MARKER);

		for (String mago : separaStringheAlleVirgole(maghi)) {

			try (Scanner scannerDiLinee = new Scanner(mago)) {

				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("Non è presente il nome della stanza" + mago));
				String nomeStanza = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("Non è presente il nome del cane" + mago));
				String nomeMago = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("Non è presente la presentazione ") + mago);
				String presentazione = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(),
						msgTerminazionePrecoce("Non è presente il nome dell'attrezzo ") + mago);
				String attrezzoMago = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("Non è presente peso dell'attrezzo ") + mago);
				Integer pesoAttrezzo = Integer.parseInt(scannerDiLinee.next());
				
				
				AbstractPersonaggio nuovaMago = new Mago(nomeMago, presentazione, new Attrezzo(attrezzoMago, pesoAttrezzo));
				this.nome2stanza.get(nomeStanza).setPersonaggio(nuovaMago);
			}
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext()) { //aggiunto
				result.add(scannerDiParole.next());
			}
		}
		return result;
	}

	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale + " non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for (String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il peso dell'attrezzo " + nomeAttrezzo + "."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce(
						"il nome della stanza in cui collocare l'attrezzo " + nomeAttrezzo + "."));
				nomeStanza = scannerLinea.next();
			}
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza)
			throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),
					"Attrezzo " + nomeAttrezzo + " non collocabile: stanza " + nomeStanza + " inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		} catch (NumberFormatException e) {
			check(false, "Peso attrezzo " + nomeAttrezzo + " non valido");
		}
	}

	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specifiche : separaStringheAlleVirgole(specificheUscite)) { //aggiunto
			try (Scanner scannerDiLinea = new Scanner(specifiche)) {

				while (scannerDiLinea.hasNext()) { 
					check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),
						msgTerminazionePrecoce("la direzione di una uscita della stanza " + stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(), msgTerminazionePrecoce(
						"la destinazione di una uscita della stanza " + stanzaPartenza + " nella direzione " + dir));
					String stanzaDestinazione = scannerDiLinea.next();

					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			}
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere " + msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa), "Stanza di partenza sconosciuta " + dir);
		check(isStanzaValida(nomeA), "Stanza di destinazione sconosciuta " + dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}

	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore)
			throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException(
					"Formato file non valido [" + this.reader.getLineNumber() + "] " + messaggioErrore);
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}
