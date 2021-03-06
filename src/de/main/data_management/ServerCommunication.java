package de.main.data_management;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import de.main.communication.Connection;
import de.main.communication.Send;

/**
 * Bietet eine Schnittstelle fuer das Androidpaket um die Datenressourcen des
 * Clients zu veraendern und die Aenderung mit dem zum Server zu kommunizieren
 */
public class ServerCommunication {

	/**
	 * Stellt eine Verbindung zum Server her
	 */
	public static synchronized void connect() {
		Connection.getConnection().setConnecting();
	}

	/**
	 * Trennt die Verbindung zum Server
	 */
	public static synchronized void terminateThread() {
		Connection.getConnection().terminateThread();
	}

	/**
	 * Steuert den Fahrstrom am Server
	 * 
	 * @param status
	 *            - boolean - true = "Power On"
	 */
	public static synchronized void sendPower(boolean status) {
		if (status == true) {
			Send.sendPowerOn();
		} else {
			Send.sendPowerOff();
		}
	}

	/**
	 * Sendet Nothalt fuer alle Zuege
	 */
	public static synchronized void sendPanic() {
		Send.sendPanic();
	}

	/**
	 * Gibt eine Liste mit Fehlermeldungen zurueck
	 * 
	 * @return StringList - Liste mit Fehlermeldungen
	 */
	public static synchronized List<String> getErrorList() {
		return Connection.getErrorList();
	}

	/**
	 * Gibt die Zugnummer eines bestimmten Zuges zurueck
	 * 
	 * @param key
	 *            - int - Zugnummer
	 * @return int - Zugnummer
	 */
	public static synchronized int getTrainNumber(int key) {
		return TrainDepotMap.getTrainDepot().getTrainMapEntry(key)
				.getTrainNumber();
	}

	/**
	 * Gibt die Zugfunktionen Licht bis F7 eines bestimmten Zuges zurueck
	 * 
	 * @param key
	 *            - int - Zugnummer
	 * @return byte - Zugfunktionen Licht bis F7
	 */
	public static synchronized byte getModeF0F7(int key) {
		return TrainDepotMap.getTrainDepot().getTrainMapEntry(key)
				.getModeF0F7();
	}

	/**
	 * Speichert Zugfunktionen Licht bis F7 und kommuniziert Veraenderung mit
	 * dem Server
	 * 
	 * @param key
	 *            - int - Zugnummer
	 * @param copyByte
	 *            - byte - Zugfunktionen Licht bis F7
	 */
	public static synchronized void setModeF0F7(int key, byte copyByte) {
		TrainObject selectedTrainObject = TrainDepotMap.getTrainDepot()
				.getTrainMapEntry(key);
		selectedTrainObject.setModeF0F7(copyByte);
		Send.send0x28(selectedTrainObject.getTrainNumber(),
				selectedTrainObject.getModeF0F7(),
				selectedTrainObject.getModeF8F15(),
				selectedTrainObject.getModeF16F23());
	}

	/**
	 * Gibt die Zugfunktionen F8 bis F15 eines bestimmten Zuges zurueck
	 * 
	 * @param key
	 *            - int - Zugnummer
	 * @return byte - Zugfunktionen F8 bis F15
	 */
	public static synchronized byte getModeF8F15(int key) {
		return TrainDepotMap.getTrainDepot().getTrainMapEntry(key)
				.getModeF8F15();
	}

	/**
	 * Speichert Zugfunktionen F8 bis F15 und kommuniziert Veraenderung mit dem
	 * Server
	 * 
	 * @param key
	 *            - int - Zugnummer
	 * @param copyByte
	 *            - byte - Zugfunktionen F8 bis F15
	 */
	public static synchronized void setModeF8F15(int key, byte copyByte) {
		TrainObject selectedTrainObject = TrainDepotMap.getTrainDepot()
				.getTrainMapEntry(key);
		selectedTrainObject.setModeF8F15(copyByte);
		Send.send0x28(selectedTrainObject.getTrainNumber(),
				selectedTrainObject.getModeF0F7(),
				selectedTrainObject.getModeF8F15(),
				selectedTrainObject.getModeF16F23());
	}

	/**
	 * Gibt die Zugfunktion F16 eines bestimmten Zuges zurueck
	 * 
	 * @param key
	 *            - int - Zugnummer
	 * @return byte - Zugfunktion F16
	 */
	public static synchronized byte getModeF16F23(int key) {
		return TrainDepotMap.getTrainDepot().getTrainMapEntry(key)
				.getModeF16F23();
	}

	/**
	 * Speichert Zugfunktion F16 und kommuniziert Veraenderung mit dem Server
	 * 
	 * @param key
	 *            - int - Zugnummer
	 * @param copyByte
	 *            - byte - Zugfunktion F16
	 */
	public static synchronized void setModeF16F23(int key, byte copyByte) {
		TrainObject selectedTrainObject = TrainDepotMap.getTrainDepot()
				.getTrainMapEntry(key);
		selectedTrainObject.setModeF16F23(copyByte);
		Send.send0x28(selectedTrainObject.getTrainNumber(),
				selectedTrainObject.getModeF0F7(),
				selectedTrainObject.getModeF8F15(),
				selectedTrainObject.getModeF16F23());
	}

	/**
	 * Gibt die Zugrichtung eines bestimmten Zuges zurueck
	 * 
	 * @param key
	 *            - int - Zugnummer
	 * @return byte - Zugrichtung
	 */
	public static synchronized byte getDirection(int key) {
		return TrainDepotMap.getTrainDepot().getTrainMapEntry(key)
				.getDirection();
	}

	/**
	 * Speichert Zugrichtung und kommuniziert Veraenderung mit dem Server
	 * 
	 * @param key
	 *            - int - Zugnummer
	 * @param Byte
	 *            - byte - Zugrichtung
	 */
	public static synchronized void setDirection(int key, byte Byte) {
		TrainObject selectedTrainObject = TrainDepotMap.getTrainDepot()
				.getTrainMapEntry(key);
		selectedTrainObject.setDirection(Byte);
		Send.send0x24(selectedTrainObject.getTrainNumber(),
				selectedTrainObject.getRunningNotch(),
				selectedTrainObject.getDirection());
	}

	/**
	 * Gibt die Zuggeschwindigkeit eines bestimmten Zuges zurueck
	 * 
	 * @param key
	 *            - int - Zugnummer
	 * @return int - Zuggeschwindigkeit
	 */
	public static synchronized int getRunningNotch(int key) {
		return TrainDepotMap.getTrainDepot().getTrainMapEntry(key)
				.getRunningNotch();
	}

	/**
	 * Speichert Zuggeschwindigkeit und kommuniziert Veraenderung mit dem Server
	 * 
	 * @param key
	 *            - int - Zugnummer
	 * @param runningNotch
	 *            - int - Zuggeschwindigkeit
	 */
	public static synchronized void setRunningNotch(int key, int runningNotch) {
		TrainObject selectedTrainObject = TrainDepotMap.getTrainDepot()
				.getTrainMapEntry(key);
		selectedTrainObject.setRunningNotch(runningNotch);
		Send.send0x24(selectedTrainObject.getTrainNumber(),
				selectedTrainObject.getRunningNotch(),
				selectedTrainObject.getDirection());
	}

	/**
	 * Gibt die maximalen Fahrstufen eines bestimmten Zuges zurueck
	 * 
	 * @param key
	 *            - int - Zugnummer
	 * @return int - maximalen Fahrstufen
	 */
	public static synchronized int getMaxRunningNotch(int key) {
		return TrainDepotMap.getTrainDepot().getTrainMapEntry(key)
				.getMaxRunningNotch();
	}

	/**
	 * Gibt den Opmode eines bestimmten Zuges zurueck
	 * 
	 * @param key
	 *            - int - Zugnummer
	 * @return byte - Opmode
	 */
	public static synchronized byte getOpmode(int key) {
		return TrainDepotMap.getTrainDepot().getTrainMapEntry(key).getOpmode();
	}

	/**
	 * Prueft ob ein bestimmter Zug existiert
	 * 
	 * @param key
	 *            - int - Zugnummer
	 * @return boolean - true = "Zug existiert"
	 */
	public static synchronized boolean isTrainExisting(int key) {
		return TrainDepotMap.getTrainDepot().isTrainExisting(key);
	}

	/**
	 * Gibt eine Liste aller Zugname in der Hashmap zurueck
	 * 
	 * @return StringArrayList
	 */
	public static synchronized ArrayList<String> generateTrainNameList() {
		return TrainDepotMap.getTrainDepot().generateTrainNameList();
	}

	/**
	 * Gibt ein Switchboard Objekt aus der Hashmap zurueck
	 * 
	 * @param key
	 *            - String - Name der BusAdresse, "RMX 1|110"
	 * @return Switchboard
	 */
	public static synchronized SwitchBoard getSwitchBoardEntry(String key) {
		return SwitchBoardMap.getSwitchBoardMap().getSwitchBoardEntry(key);
	}

	/**
	 * Gibt das Byte einer bestimmten Adresse im Schaltpultes zurueck
	 * 
	 * @param key
	 *            - String - Name der BusAdresse ("RMX 1|110")
	 * @return byte - Byte
	 */
	public static synchronized byte getBytes(String key) {
		return SwitchBoardMap.getSwitchBoardMap().getSwitchBoardEntry(key)
				.getBytes();
	}

	/**
	 * Speichert uebergebenes Byte in BusAdresse und kommuniziert Veraenderung
	 * mit dem Server
	 * 
	 * @param key
	 *            - String - bspw. "RMX 1|110"
	 * @param intByte
	 *            - byte - zu setzendes Byte
	 * @param address
	 *            - String - Name des Buttons
	 */
	public static synchronized void setBytes(String key, byte intByte,
			String address) {
		SwitchBoardMap.getSwitchBoardMap().getSwitchBoardEntry(key)
				.setBytes((byte) intByte);
		// byte outputByte =
		// ServerCommunication.getSwitchBoardEntry(key).getBytesForServer();
		System.out.println("SEND TLE: Schalte Key " + key + " und Adresse "
				+ address + "." + "Setze Byte auf "
				+ Integer.toHexString(intByte));
		Send.send0x05((byte) Integer.parseInt(address), intByte);
	}

	/**
	 * Gibt das Namen einer bestimmten Adresse im Schaltpultes zurueck
	 * 
	 * @param key
	 *            - String - Name der BusAdresse
	 * @return String
	 */
	public static synchronized String getNumber(String key) {
		return SwitchBoardMap.getSwitchBoardMap().getSwitchBoardEntry(key)
				.getAddress();
	}

	/**
	 * Inilialisiert die Hashmap fuer das Schaltpult
	 */
	public static synchronized void generateMap() {
		SwitchBoardMap.getSwitchBoardMap().generateMap();
	}

	/**
	 * Gibt eine Liste aller Bus-Namen in der Hashmap zurueck
	 * 
	 * @return StringArray
	 */
	public static synchronized String[] getBusContainer() {
		return SwitchBoardMap.getSwitchBoardMap().getBusContainer();
	}
}
