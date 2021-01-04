package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerSingleton {
	
	private static Logger e720Logger = LogManager.getLogger("e720");
	
	public static Logger getLogger() {
		return e720Logger;
	}

}
