//============================================================================
// Name        : Assignment 2
// Author      : Rishabh Sahoo
// Date        : 1/24/22
// Description : Automobile class for the Car Rental website application
//============================================================================
package model;

import java.lang.ArrayIndexOutOfBoundsException;

public class Automobile implements java.io.Serializable {
	private static final long serialVersionUID = 1362422403381823640L;
	private String makeName, modelName, packageName, typeName, year;
	private double basePrice; // double is not an exact decimal.
	private OptionSet optionSetList[];
	private int optionSetListListLength;

	/*
	 * Constructor
	 */
	public Automobile() {
		/*
		 * We don't know the size so let's just make it size 10 and resize it later if
		 * need be It would have been nice to use a List<>
		 */
		int size = 20;
		makeName = "";
		modelName = "";
		packageName = "";
		typeName = "";
		year = "";
		basePrice = 0;
		optionSetList = new OptionSet[size];
		optionSetListListLength = 0;
		for (int i = 0; i < size; i++)
			optionSetList[i] = new OptionSet();
	}

	public Automobile(int size) {
		makeName = "";
		modelName = "";
		packageName = "";
		typeName = "";
		year = "";
		basePrice = 0;
		optionSetList = new OptionSet[size];
		optionSetListListLength = 0;
		for (int i = 0; i < size; i++)
			optionSetList[i] = new OptionSet();
	}

	/*
	 * Getter
	 */
	// Get Name of Automotive
	public String getMake() {
		return makeName;
	}

	public String getModel() {
		return modelName;
	}

	public String getPackage() {
		return packageName;
	}

	public String getType() {
		return typeName;
	}

	public String getYear() {
		return year;
	}

	// Get Automotive Base Price
	public double getPrice() {
		return basePrice;
	}

	// Get OptionSet (by index value)
	public OptionSet getOptionSet(int index) {
		OptionSet optionSetObject = null;
		try {
			optionSetObject = optionSetList[index];
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return optionSetObject;
	}

	public int length() {
		return optionSetListListLength;
	}

	/*
	 * Find
	 */
	// Find OptionSet with name
	public OptionSet findOptionSet(String name) {
		OptionSet optionSetObject = null;
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i].getName() == name) {
				optionSetObject = optionSetList[i];
			}
		}
		return optionSetObject;
	}

	public Option findOptionSetOption(String optionSetName, String optionName) {
		Option optionObject = null;
		OptionSet optionSetObject = findOptionSet(optionSetName);
		if (optionSetObject != null) {
			optionObject = optionSetObject.findOptionSet(optionName);
		}
		return optionObject;
	}

	/*
	 * Setter
	 */
	// SetName
	public void setMake(String name) {
		makeName = name;
	}

	public void setModel(String name) {
		modelName = name;
	}

	public void setPackage(String name) {
		packageName = name;
	}

	public void setType(String name) {
		typeName = name;
	}

	public void setYear(String name) {
		year = name;
	}

	// Set Base Price
	public void setPrice(double price_) {
		basePrice = price_;
	}

	public int setOptionSet(String name) {
		OptionSet optionSetObject = getOptionSet(optionSetListListLength);
		optionSetObject.setName(name);
		return optionSetListListLength++;
	}

	public boolean setOptionSetName(String optionSetName, String nameNew) {
		boolean returnValue = false;
		OptionSet optionSetObject = findOptionSet(optionSetName);
		if (optionSetObject != null) {
			optionSetObject.setName(nameNew);
			returnValue = true;
		}
		return returnValue;
	}

	// Set values of OptionSet
	public int setOptionSetOption(int indexSet, String name, double price_) {
		int indexReturn = -1;
		OptionSet optionSetObject = getOptionSet(indexSet);
		indexReturn = optionSetObject.setOption(name, price_);
		// we set the 
		if (optionSetObject.getName().equals("Make")) {
			setMake(name);
		} else if (optionSetObject.getName().equals("Model")) {
			setModel(name);
		} else if (optionSetObject.getName().equals("Package")) {
			setPackage(name);
		} else if (optionSetObject.getName().equals("Type")) {
			setType(name);
		} else if (optionSetObject.getName().equals("Year")) {
			setYear(name);
		} else if (optionSetObject.getName().equals("Retail Price")) {
			/*
			 * Price may be set by either Retail Price: 18445 or Retail Price: /18445
			 */
			if (name.equals("")) {
				setPrice(price_);
			} else {
				setPrice(Double.parseDouble(name));
			}
		}
		return indexReturn;
	}

	public boolean setOptionSetOptionName(String optionSetName, String optionName, String nameNew) {
		boolean returnValue = false;
		Option optionObject = findOptionSetOption(optionSetName, optionName);
		if (optionObject != null) {
			optionObject.setName(optionName);
			returnValue = true;
		}
		return returnValue;
	}

	public boolean setOptionSetOptionPrice(String optionSetName, String optionName, double priceNew) {
		boolean returnValue = false;
		Option optionObject = findOptionSetOption(optionSetName, optionName);
		if (optionObject != null) {
			optionObject.setPrice(priceNew);
			returnValue = true;
		}
		return returnValue;
	}

	/*
	 * print() and toString()
	 */
	public void print() {
		System.out.print(toString());
	}

	public String toString() {
		StringBuffer stringBufferObject;
		int i, n;
		n = length();
		stringBufferObject = new StringBuffer("");
		stringBufferObject.append("Year, Make, Model: ").append(getYear()).append(", ").append(getMake()).append(", ")
				.append(getModel()).append(" and Base Price: $").append(getPrice());
		stringBufferObject.append(System.getProperty("line.separator"));
		for (i = 0; i < n; i++) {
			stringBufferObject.append(optionSetList[i].toString()).append(System.getProperty("line.separator"));
		}
		return stringBufferObject.toString();
	}

	
		
	}

