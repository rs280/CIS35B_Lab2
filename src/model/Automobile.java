//============================================================================
// Name        : Assignment 1
// Author      : Branden Lee
// Date        : 4/24/2018
// Description : Automobile class for the KBB website application
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

	public OptionSet.Option findOptionSetOption(String optionSetName, String optionName) {
		OptionSet.Option optionObject = null;
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
		OptionSet.Option optionObject = findOptionSetOption(optionSetName, optionName);
		if (optionObject != null) {
			optionObject.setName(optionName);
			returnValue = true;
		}
		return returnValue;
	}

	public boolean setOptionSetOptionPrice(String optionSetName, String optionName, double priceNew) {
		boolean returnValue = false;
		OptionSet.Option optionObject = findOptionSetOption(optionSetName, optionName);
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

	protected class OptionSet implements java.io.Serializable {
		private static final long serialVersionUID = 5846223453457830887L;
		private Option optionList[];
		private String optionSetName;
		private int optionListLength;

		/*
		 * Constructor
		 */
		protected OptionSet() {
			/*
			 * We don't know the size so let's just make it size 12 and resize it later if
			 * need be It would have been nice to use a List<>
			 */
			int size = 12;
			optionList = new Option[size];
			optionSetName = "";
			optionListLength = 0;
			for (int i = 0; i < size; i++)
				optionList[i] = new Option();
		}

		protected OptionSet(String name, int size) {
			optionList = new Option[size];
			optionSetName = name;
			optionListLength = 0;
			for (int i = 0; i < size; i++)
				optionList[i] = new Option();
		}

		/*
		 * Getter
		 */
		protected String getName() {
			return optionSetName;
		}

		// Get OptionSet (by index value)
		protected Option getOption(int index) {
			Option optionObject = null;
			try {
				optionObject = optionList[index];
			} catch (Exception e) {
				e.printStackTrace();
			}
			return optionObject;
		}

		protected int length() {
			return optionListLength;
		}

		/*
		 * Find
		 */
		// Find Option with name (in context of OptionSet)
		protected Option findOptionSet(String name) {
			Option optionObject = null;
			for (int i = 0; i < optionList.length; i++) {
				try {
					if (optionList[i].getName() == name) {
						optionObject = optionList[i];
					}
				} catch (NullPointerException e) {
					/*
					 * According to Carnegie Mellon University Software Engineering Institute You
					 * should not catch a null pointer exception. BUT WE WILL FOR THE SAKE OF THE
					 * ASSIGNMENT!
					 */
					break;
				}
			}
			return optionObject;
		}

		/*
		 * Setter
		 */
		protected void setName(String name) {
			optionSetName = name;
		}

		// Set values of Option (in context of OptionSet)
		protected int setOption(String name, double price_) {
			Option optionObject = getOption(optionListLength);
			optionObject.setName(name);
			optionObject.setPrice(price_);
			return optionListLength++;
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
			stringBufferObject.append(getName()).append(": ");
			for (i = 0; i < n; i++) {
				stringBufferObject.append(getOption(i).toString());
				if (i < n - 1) {
					stringBufferObject.append(", ");
				}
			}
			return stringBufferObject.toString();
		}

		protected class Option implements java.io.Serializable {
			private static final long serialVersionUID = 2272307185575003314L;
			private String optionName;
			private double price;

			/*
			 * Constructor
			 */
			protected Option() {
				optionName = "";
				price = 0;
			}

			protected Option(String name, double price_) {
				optionName = name;
				price = price_;
			}

			/*
			 * Getter
			 */
			protected String getName() {
				return optionName;
			}

			protected double getPrice() {
				return price;
			}

			/*
			 * Setter
			 */
			protected void setName(String name) {
				optionName = name;
			}

			protected void setPrice(double price_) {
				price = price_;
			}

			/*
			 * print() and toString()
			 */
			public void print() {
				System.out.print(toString());
			}

			public String toString() {
				StringBuffer stringBufferObject;
				stringBufferObject = new StringBuffer("");
				stringBufferObject.append(getName()).append(" for $").append(getPrice());
				return stringBufferObject.toString();
			}
		}
	}
}
