//============================================================================
// Name        : Assignment 2
// Author      : Rishabh Sahoo
// Date        : 1/17/21
// Description : Driver class for the Car Rental application
//============================================================================

package driver;

//import model.Automobile;
import adapter.*;

public class Driver {

	public static void main(String[] args) {
		BuildAuto buildAutoInterface = new BuildAuto();
		// Build Automobile Object from a file.
		String automobileKey = buildAutoInterface.buildAuto("FordZTW.txt");
		if (automobileKey != null) {
			// Print attributes before serialization
			buildAutoInterface.printAuto(automobileKey);
			// Serialize the object
			buildAutoInterface.serialize(automobileKey, "FordZTW.data");
		} else {
			System.out.println("Could not build automobile");
		}
		// update an options set's name
		buildAutoInterface.updateOptionSetName(automobileKey, "Color", "Colors");
		// update an options set's option price
		buildAutoInterface.updateOptionPrice(automobileKey, "Transmission", "automatic", 50);
		// Deserialize the object and read it into memory.
		String automobileKey2 = buildAutoInterface.deserialize("FordZTW.data");
		// Print new attributes
		if (automobileKey2 != null) {
			buildAutoInterface.printAuto(automobileKey2);
		} else {
			System.out.println("could not deserialize automobile");
		}
	}

}
