package exception;

import model.Automobile;

public class FixAutomobileTable {
	FixAutomobileTable() {

	}

	public String code500(model.Automobile automobileObject) {
		return automobileObject.getMake() + "-" + automobileObject.getModel() + "-" + automobileObject.getType() + "-"
				+ automobileObject.getPackage() + "-" + automobileObject.getYear();
	}
}
