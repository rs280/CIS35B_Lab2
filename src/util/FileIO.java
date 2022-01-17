//============================================================================
// Name        : Assignment 2
// Author      : Branden Lee
// Date        : 4/24/2018
// Description : FileIO class for the KBB website application
//============================================================================

package util;

import java.io.*;
import model.Automobile;
import exception.AutoException;

public class FileIO {
	/*
	 * File pattern: newline (\n) separates different optionSet colon ":" separates
	 * optionSetName and optionSetOptions optionSetOptions may span multiple lines
	 * as long as no new optionSetName is found comma "," separates different
	 * optionSetOptions slash "/" separates different option values
	 */
	public boolean read(String fileName, Automobile autoObj) throws AutoException {
		boolean returnValue = false;
		String optionSetOptions, optionSetName, lineNext, optionName, optionPrice;
		String[] optionParts;

		BufferedReader reader = null;
		int optionSetObjectIndex = -1;

		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
			while ((lineNext = reader.readLine()) != null) {
				// optionSet
				if (lineNext.indexOf(':') != -1) {
					String[] optionSetParts = lineNext.split(":");
					optionSetName = optionSetParts[0].trim();
					optionSetOptions = optionSetParts[1].trim();
					if (optionSetName.equals("")) {
						throw new exception.AutoException(100);
					} else {
						optionSetObjectIndex = autoObj.setOptionSet(optionSetName);
					}
					if (optionSetOptions.equals("")) {
						throw new exception.AutoException(101);
					}
				} else {
					/*
					 * whole line is options if the optionSetName not found This allows options to
					 * be split on multiple lines for file readability.
					 */
					optionSetOptions = lineNext;
				}
				// optionSet options
				if (optionSetOptions.indexOf(',') != -1 && optionSetObjectIndex != -1) {
					optionParts = optionSetOptions.split(",");
				} else {
					optionParts = new String[] { optionSetOptions };
				}
				for (String optionPart : optionParts) {
					// part not blank
					if (optionPart.trim().length() > 0) {
						if (optionPart.indexOf('/') != -1) {
							String[] optionValueParts = optionPart.split("/");
							optionName = optionValueParts[0].trim();
							optionPrice = optionValueParts[1].trim();
							if (optionName.equals("")) {
								new exception.AutoException(102, true); // warning
							}
							if (optionPrice.equals("")) {
								new exception.AutoException(103, true); // warning
							}
							autoObj.setOptionSetOption(optionSetObjectIndex, optionName,
									Double.parseDouble(optionPrice));
						} else {
							autoObj.setOptionSetOption(optionSetObjectIndex, optionPart.trim(), 0);
						}
					} else {
						new exception.AutoException(102, true); // warning
					}
				}
			}
			returnValue = true;
		} catch (FileNotFoundException e) {
			throw new exception.AutoException(200);
		} catch (IOException e) {
			throw new exception.AutoException(201);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				// nothing
			}
		}
		return returnValue;
	}

	public boolean serialize(String fileName, Automobile autoObj) throws AutoException {
		boolean returnValue = false;
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(autoObj);
			out.close();
			fileOut.close();
			returnValue = true;
		} catch (IOException e) {
			throw new exception.AutoException(300);
		}
		return returnValue;
	}

	public Automobile deserialize(String fileName) {
		Automobile autoObj = null;
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			autoObj = (Automobile) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return autoObj;
	}

	/*
	 * print() and toString()
	 */
	public void print() {
		System.out.print(toString());
	}

	public String toString() {
		return "FileIO Utility";
	}
}
