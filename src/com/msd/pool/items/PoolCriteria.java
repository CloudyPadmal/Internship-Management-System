package com.msd.pool.items;

import com.msd.pool.interfaces.Preferences;

public class PoolCriteria implements Preferences {

	boolean ARDUINO;
	boolean FPGA;
	boolean ROBOTICS;
	boolean WIFI;
	boolean ANTENNAS;
	boolean NETWORKING;
	boolean PROCESSORDESIGN;
	boolean IMAGEPROCESSING;
	boolean PROGRAMMING;
	boolean AUTOMATION;
	boolean BIOMEDICAL;
	boolean BIOMECHANICS;
	boolean TELECOM;
	boolean SEMICONDUCTORS;
	boolean CIRCUITS;
	boolean IOT;
	boolean AI;
	boolean SIGNALPROCESSING;

	public PoolCriteria() {/**/}
	
	public PoolCriteria(boolean aRDUINO, boolean fPGA, boolean rOBOTICS, boolean wIFI, boolean aNTENNAS,
			boolean nETWORKING, boolean pROCESSORDESIGN, boolean iMAGEPROCESSING, boolean pROGRAMMING,
			boolean aUTOMATION, boolean bIOMEDICAL, boolean bIOMECHANICS, boolean tELECOM, boolean sEMICONDUCTORS,
			boolean cIRCUITS, boolean iOT, boolean aI, boolean sIGNALPROCESSING) {
		ARDUINO = aRDUINO;
		FPGA = fPGA;
		ROBOTICS = rOBOTICS;
		WIFI = wIFI;
		ANTENNAS = aNTENNAS;
		NETWORKING = nETWORKING;
		PROCESSORDESIGN = pROCESSORDESIGN;
		IMAGEPROCESSING = iMAGEPROCESSING;
		PROGRAMMING = pROGRAMMING;
		AUTOMATION = aUTOMATION;
		BIOMEDICAL = bIOMEDICAL;
		BIOMECHANICS = bIOMECHANICS;
		TELECOM = tELECOM;
		SEMICONDUCTORS = sEMICONDUCTORS;
		CIRCUITS = cIRCUITS;
		IOT = iOT;
		AI = aI;
		SIGNALPROCESSING = sIGNALPROCESSING;
	}

	public boolean isARDUINO() {
		return ARDUINO;
	}

	public void setARDUINO(boolean aRDUINO) {
		ARDUINO = aRDUINO;
	}

	public boolean isFPGA() {
		return FPGA;
	}

	public void setFPGA(boolean fPGA) {
		FPGA = fPGA;
	}

	public boolean isROBOTICS() {
		return ROBOTICS;
	}

	public void setROBOTICS(boolean rOBOTICS) {
		ROBOTICS = rOBOTICS;
	}

	public boolean isWIFI() {
		return WIFI;
	}

	public void setWIFI(boolean wIFI) {
		WIFI = wIFI;
	}

	public boolean isANTENNAS() {
		return ANTENNAS;
	}

	public void setANTENNAS(boolean aNTENNAS) {
		ANTENNAS = aNTENNAS;
	}

	public boolean isNETWORKING() {
		return NETWORKING;
	}

	public void setNETWORKING(boolean nETWORKING) {
		NETWORKING = nETWORKING;
	}

	public boolean isPROCESSORDESIGN() {
		return PROCESSORDESIGN;
	}

	public void setPROCESSORDESIGN(boolean pROCESSORDESIGN) {
		PROCESSORDESIGN = pROCESSORDESIGN;
	}

	public boolean isIMAGEPROCESSING() {
		return IMAGEPROCESSING;
	}

	public void setIMAGEPROCESSING(boolean iMAGEPROCESSING) {
		IMAGEPROCESSING = iMAGEPROCESSING;
	}

	public boolean isPROGRAMMING() {
		return PROGRAMMING;
	}

	public void setPROGRAMMING(boolean pROGRAMMING) {
		PROGRAMMING = pROGRAMMING;
	}

	public boolean isAUTOMATION() {
		return AUTOMATION;
	}

	public void setAUTOMATION(boolean aUTOMATION) {
		AUTOMATION = aUTOMATION;
	}

	public boolean isBIOMEDICAL() {
		return BIOMEDICAL;
	}

	public void setBIOMEDICAL(boolean bIOMEDICAL) {
		BIOMEDICAL = bIOMEDICAL;
	}

	public boolean isBIOMECHANICS() {
		return BIOMECHANICS;
	}

	public void setBIOMECHANICS(boolean bIOMECHANICS) {
		BIOMECHANICS = bIOMECHANICS;
	}

	public boolean isTELECOM() {
		return TELECOM;
	}

	public void setTELECOM(boolean tELECOM) {
		TELECOM = tELECOM;
	}

	public boolean isSEMICONDUCTORS() {
		return SEMICONDUCTORS;
	}

	public void setSEMICONDUCTORS(boolean sEMICONDUCTORS) {
		SEMICONDUCTORS = sEMICONDUCTORS;
	}

	public boolean isCIRCUITS() {
		return CIRCUITS;
	}

	public void setCIRCUITS(boolean cIRCUITS) {
		CIRCUITS = cIRCUITS;
	}

	public boolean isIOT() {
		return IOT;
	}

	public void setIOT(boolean iOT) {
		IOT = iOT;
	}

	public boolean isAI() {
		return AI;
	}

	public void setAI(boolean aI) {
		AI = aI;
	}

	public boolean isSIGNALPROCESSING() {
		return SIGNALPROCESSING;
	}

	public void setSIGNALPROCESSING(boolean sIGNALPROCESSING) {
		SIGNALPROCESSING = sIGNALPROCESSING;
	}
	
	/**
	 * Filters out invalid preferences and create a string full of selected
	 * preferences
	 * 
	 * @return WHERE clause for a database fetch
	 */
	public String getWhereQuery() {
		String set = " = TRUE AND ";
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(isAI() ? "AI" + set : "");
		sBuilder.append(isANTENNAS() ? "ANTENNAS" + set : "");
		sBuilder.append(isARDUINO() ? "ARDUINO" + set : "");
		sBuilder.append(isAUTOMATION() ? "AUTOMATION" + set : "");
		sBuilder.append(isBIOMECHANICS() ? "BIOMECHANICS" + set : "");
		sBuilder.append(isBIOMEDICAL() ? "BIOMEDICAL" + set : "");
		sBuilder.append(isCIRCUITS() ? "CIRCUITS" + set : "");
		sBuilder.append(isFPGA() ? "FPGA" + set : "");
		sBuilder.append(isIMAGEPROCESSING() ? "IMAGEPROCESSING" + set : "");
		sBuilder.append(isIOT() ? "IOT" + set : "");
		sBuilder.append(isNETWORKING() ? "NETWORKING" + set : "");
		sBuilder.append(isPROCESSORDESIGN() ? "PROCESSORDESIGN" + set : "");
		sBuilder.append(isPROGRAMMING() ? "PROGRAMMING" + set : "");
		sBuilder.append(isROBOTICS() ? "ROBOTICS" + set : "");
		sBuilder.append(isSEMICONDUCTORS() ? "SEMICONDUCTORS" + set : "");
		sBuilder.append(isSIGNALPROCESSING() ? "SIGNALPROCESSING" + set : "");
		sBuilder.append(isTELECOM() ? "TELECOM" + set : "");
		sBuilder.append(isWIFI() ? "WIFI" + set : "");
		// Prepare the return statement
		String rawQuery = sBuilder.toString();
		if (rawQuery.contains(set)) {
			rawQuery = " WHERE " + rawQuery.substring(0, rawQuery.length() - 5);
			return rawQuery;
		} else {
			return null;
		}
	}
}
