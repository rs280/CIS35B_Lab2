//============================================================================
// Name        : Assignment 2
// Author      : Rishabh Sahoo
// Date        : 1/17/22
// Description : Driver class for the Car Rental application
//============================================================================

package driver;

//import model.Automobile;
import adapter.*;

public class Driver {

	public static void main(String[] args) {
		BuildAuto createAuto = new BuildAuto();
		// Build Automobile Object from a file.
		String automobileKey = createAuto.buildAuto("FordZTW.txt");
		if (automobileKey != null) {
			// Print attributes before serialization
			createAuto.printAuto(automobileKey);
			// Serialize the object
			createAuto.serialize(automobileKey, "FordZTW.data");
		} else {
			System.out.println("Could not build automobile");
		}
		// update an options set's name
		createAuto.updateOptionSetName(automobileKey, "Color", "Colors");
		// update an options set's option price
		createAuto.updateOptionPrice(automobileKey, "Transmission", "automatic", 50);
		// Deserialize the object and read it into memory.
		String automobileKey2 = createAuto.deserialize("FordZTW.data");
		// Print new attributes
		if (automobileKey2 != null) {
			createAuto.printAuto(automobileKey2);
		} else {
			System.out.println("could not deserialize automobile");
		}
	}

}
