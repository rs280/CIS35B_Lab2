package adapter;

import model.*;
import exception.*;

public abstract class ProxyAutomobile {
	private model.AutomobileTable automobileTable;
	private util.FileIO autoutil;

	ProxyAutomobile() {
		automobileTable = new AutomobileTable(64);
		autoutil = new util.FileIO();
	}

	public boolean updateOptionSetName(String automobileKey, String optionSetName, String nameNew) {
		boolean returnValue = false;
		model.Automobile automobileObject = automobileTable.getByKey(automobileKey);
		if (automobileObject != null) {
			automobileObject.setOptionSetName(optionSetName, nameNew);
			returnValue = true;
		}
		return returnValue;
	}

	public boolean updateOptionPrice(String automobileKey, String optionSetName, String optionName, double priceNew) {
		boolean returnValue = false;
		model.Automobile automobileObject = automobileTable.getByKey(automobileKey);
		if (automobileObject != null) {
			automobileObject.setOptionSetOptionPrice(optionSetName, optionName, priceNew);
			returnValue = true;
		}
		return returnValue;
	}

	public String buildAuto(String filename) {
		String returnValue = null;
		model.Automobile automobileObject = new model.Automobile();
		try {
			autoutil.read("FordZTW.txt", automobileObject);
			returnValue = automobileTable.insertWrapper(automobileObject);
		} catch (exception.AutoException e) {
			// double check that return value is null
			returnValue = null;
		}
		return returnValue;
	}

	public boolean printAuto(String automobileKey) {
		boolean returnValue = false;
		model.Automobile automobileObject = automobileTable.getByKey(automobileKey);
		if (automobileObject != null) {
			returnValue = true;
			System.out.println(automobileObject.toString());
		}
		return returnValue;
	}

	public boolean serialize(String automobileKey, String fileName) {
		boolean returnValue = false;
		model.Automobile automobileObject;
		try {
			automobileObject = automobileTable.getByKey(automobileKey);
			autoutil.serialize(fileName, automobileObject);
			returnValue = true;
		} catch (exception.AutoException e) {
			// nothing
		}
		if(returnValue) {
			System.out.println("Serialized data is saved in " + fileName);
		} else {
			System.out.println("Automobile could not be serialized");
		}
		return returnValue;
	}

	public String deserialize(String fileName) {
		String returnValue = null;
		model.Automobile automobileObject = autoutil.deserialize(fileName);
		if (automobileObject != null) {
			System.out.println("Deserialized data read from " + fileName);
			returnValue = automobileTable.insertWrapper(automobileObject);
		} else {
			System.out.println("Automobile could not be deserialized");
		}
		return returnValue;
	}
}