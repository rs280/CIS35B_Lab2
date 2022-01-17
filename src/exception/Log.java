package exception;

import java.util.logging.*;
import java.io.*;

public class Log {
	Logger logger;
	FileHandler fh;

	public Log() {
		logger = Logger.getLogger("MyLog");
		// logger.setUseParentHandlers(false);
	}

	public void error(String str) {
		try {
			// configure the logger with handler and formatter
			fh = new FileHandler("log.txt", true);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.warning(str);
		try {
			if (fh != null) {
				fh.close();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public void warning(String str) {
		try {
			// configure the logger with handler and formatter
			fh = new FileHandler("log.txt", true);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.warning(str);
		try {
			if (fh != null) {
				fh.close();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
}