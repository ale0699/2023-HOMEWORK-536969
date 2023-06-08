package it.uniroma3.diadia;

import java.io.IOException;
import java.util.Properties;

public class Configurazione {

	private static final String DIADIA_PROPERTIES_FILE = "diadia.properties";
	private static final String CFU = "CFU_INIZIALI";
	private static final String PESO_MAX = "PESO_MAX_BORSA";
	private static Properties prop = null;

	public static int getCFU() {
		if (prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(CFU));
	}

	public static int getPesoMax() {
		if (prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(PESO_MAX));
	}

	public static void carica() {

		prop = new Properties();

		try {
			prop.load(Configurazione.class.getClassLoader().getResourceAsStream(DIADIA_PROPERTIES_FILE));
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
}
