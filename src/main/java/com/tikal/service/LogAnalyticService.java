package com.tikal.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.tikal.domain.LogEntry;

/**
 *	Service to handle log analytics
 */
public class LogAnalyticService {

	private static final String LOG_EXT = "log";

	private String loggingDir = "./src/main/resources/logs";

	private Charset charset;
	
	
	public LogAnalyticService() {
		init();
	}
	
	private void init(){
		charset = Charset.forName("ISO-8859-1");
	}

	
	/**
	 * Helper method to parse lines.
	 * @param path
	 * @return
	 */
	private Stream<String> getLines(final Path path) {
		try {
			
			return Files.lines(path, charset);
			
		} catch (final IOException e) {
			System.out.println(("Failed to process "+path));
			return Stream.empty();
		}
	}

	// ========================================================================
	// =======		     IMPLEMENT								===============
	// ========================================================================
	
	/**
	 * 1)	Stream the logs file (onlt ".log" files).
	 * 		Take only the first one and stream it's lines. 
	 * 		Count them.
	 */
	public Long findNumberOfLinesInFirstLog() throws IOException {
		return null;
	}
	
	
	/**
	 * 2)	Stream all log entries of all log files in the logs folder.
	 * 		Use LogEntry.parse() method.
	 * 
	 * 		NOTICE:	parse method might return a null value - don't return it.
	 */
	public Stream<LogEntry> streamLogs() {
		return null;
	}
	
	
	/**
	 * 3.	Get a list of all the errors that occurred in all 
	 * 		log	files.
	 * 		Use streamLogs() function.
	 */
	public List<LogEntry> findAllErrorLogs() {
		return null;
	}
	
	/**
	 * 4.	Get a list of last 10 errors.
	 * 		Last 10 errors can be listed in either one file or maybe
	 * 		in all of them.
	 * 		
	 * 		Use streamLogs() function.
	 */
	public List<LogEntry> findLast10ErrorLogs() {
		return null;
	}
	
	/**
	 * 5) 	Find if there are empty responses
	 */
	public boolean wereThereEmptyResponses() {
		return false;
	}
	
	/**
	 * 6) 	Find if there where requests after 1995
	 */
	public boolean wereThereRequestsAfter1995() {
		return false;
	}
	
	/**
	 * 7) 	Create a generic predicate method
	 */
	public boolean wereThere(Predicate<LogEntry> predicate) {
		return false;
	}
	
	/**
	 * 8) 	Map log entries  by dates.
	 */
	public Map<LocalDate, List<LogEntry>> mapByDates() {
		return null;
	}
	
	/**
	 * 9) 	Sum up all bytes that were sent as a response.
	 */
	public Optional<Integer> findHowManyBytesSent() {
		return null;
	}
	
	/**
	 * 10)	Count how many GET requests there where
	 */
	public Long HowManyGets() {
		return null;
	}
}

