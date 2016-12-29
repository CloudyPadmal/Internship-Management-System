package com.msd.items;

import java.util.ArrayList;
import java.util.List;

import com.msd.pool.interfaces.Preferences;
import com.msd.pool.items.PoolCriteria;

public class ListPrefConverter implements Preferences {

	public PoolCriteria convertListToPref(List<String> preferences) {
		// Initiate a criteria
		PoolCriteria criteria = new PoolCriteria();
		for (String element : preferences) {
			switch (element) {
			case ANTENNAS:
				criteria.setANTENNAS(true);
				break;
			case AI:
				criteria.setAI(true);
				break;
			case ARDUINO:
				criteria.setARDUINO(true);
				break;
			case AUTOMATION:
				criteria.setAUTOMATION(true);
				break;
			case BIOMECHANICS:
				criteria.setBIOMECHANICS(true);
				break;
			case BIOMEDICAL:
				criteria.setBIOMEDICAL(true);
				break;
			case CIRCUITS:
				criteria.setCIRCUITS(true);
				break;
			case FPGA:
				criteria.setFPGA(true);
				break;
			case IMAGEPROCESSING:
				criteria.setIMAGEPROCESSING(true);
				break;
			case IOT:
				criteria.setIOT(true);
				break;
			case NETWORKING:
				criteria.setNETWORKING(true);
				break;
			case PROCESSORDESIGN:
				criteria.setPROCESSORDESIGN(true);
				break;
			case PROGRAMMING:
				criteria.setPROGRAMMING(true);
				break;
			case ROBOTICS:
				criteria.setROBOTICS(true);
				break;
			case SEMICONDUCTORS:
				criteria.setSEMICONDUCTORS(true);
				break;
			case SIGNALPROCESSING:
				criteria.setSIGNALPROCESSING(true);
				break;
			case TELECOM:
				criteria.setTELECOM(true);
				break;
			case WIFI:
				criteria.setWIFI(true);
				break;
			default:
				break;
			}
		}
		return criteria;
	}

	public List<String> convertPrefToList(PoolCriteria criteria) {

		List<String> preferences = new ArrayList<>();

		if (criteria.isAI()) {
			preferences.add(AI);
		}
		if (criteria.isANTENNAS()) {
			preferences.add(ANTENNAS);
		}
		if (criteria.isARDUINO()) {
			preferences.add(ARDUINO);
		}
		if (criteria.isAUTOMATION()) {
			preferences.add(AUTOMATION);
		}
		if (criteria.isBIOMECHANICS()) {
			preferences.add(BIOMECHANICS);
		}
		if (criteria.isBIOMEDICAL()) {
			preferences.add(BIOMEDICAL);
		}
		if (criteria.isCIRCUITS()) {
			preferences.add(CIRCUITS);
		}
		if (criteria.isFPGA()) {
			preferences.add(FPGA);
		}
		if (criteria.isIMAGEPROCESSING()) {
			preferences.add(IMAGEPROCESSING);
		}
		if (criteria.isIOT()) {
			preferences.add(IOT);
		}
		if (criteria.isPROCESSORDESIGN()) {
			preferences.add(PROCESSORDESIGN);
		}
		if (criteria.isPROGRAMMING()) {
			preferences.add(PROGRAMMING);
		}
		if (criteria.isROBOTICS()) {
			preferences.add(ROBOTICS);
		}
		if (criteria.isTELECOM()) {
			preferences.add(TELECOM);
		}
		if (criteria.isSEMICONDUCTORS()) {
			preferences.add(SEMICONDUCTORS);
		}
		if (criteria.isSIGNALPROCESSING()) {
			preferences.add(SIGNALPROCESSING);
		}
		if (criteria.isNETWORKING()) {
			preferences.add(NETWORKING);
		}
		if (criteria.isWIFI()) {
			preferences.add(WIFI);
		}
		return preferences;
	}
}
