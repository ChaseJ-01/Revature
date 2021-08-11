import org.apache.log4j.*;

public class test3 {

	private static final Logger logger = LogManager.getLogger(test3.class);
	
	public static void main(String[] args) {
		ConsoleAppender ca = new ConsoleAppender();
		ca.setThreshold(Level.WARN);
		ca.setLayout(new PatternLayout("%d - %p [%c]: %m%n"));
		ca.activateOptions();
		LogManager.getRootLogger().addAppender(ca);
		logger.warn("Debug");
		logger.info("Info");
	}
	
	
}
