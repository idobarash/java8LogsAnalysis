package com.tikal.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.tikal.domain.LogEntry;


public class LogAnalyticServiceTest {

	private LogAnalyticService service = new LogAnalyticService();
	
	@Test
	public void test1_FindNumberOfLinesInFirstLog() throws IOException {
		Long result = service.findNumberOfLinesInFirstLog();
		Assert.assertNotNull(result);
		Assert.assertEquals(5000, result.longValue());
	}
	
	@Test
	public void test2_StreamAllLogEntries() {
		Stream<LogEntry> logEntries = service.streamLogs();
		Assert.assertNotNull(logEntries);
		Assert.assertEquals(15000, logEntries.count());
	}

	@Test
	public void test3_findAllErrorLogs() {
		List<LogEntry> errors = service.findAllErrorLogs();
		Assert.assertNotNull(errors);
		
		boolean noErrors = errors.stream().anyMatch(le -> le.getResponse() < 500);
		Assert.assertFalse(noErrors);
	}
	

	@Test
	public void test4_findLast10ErrorLogs() {
		List<LogEntry> errors = service.findLast10ErrorLogs();
		Assert.assertNotNull(errors);
		
		Assert.assertEquals(10, errors.size());
		
		boolean noErrors = errors.stream().anyMatch(le -> le.getResponse() < 500);
		Assert.assertFalse(noErrors);
	}
	
	@Test
	public void test5_wereThereEmptyRespones() {
		
		boolean result = service.wereThereEmptyResponses();
		
		List<LogEntry> logEntries = service.streamLogs().collect(Collectors.toList());
		boolean emptyFound = false;
		Iterator<LogEntry> iterator = logEntries.iterator();
		while (emptyFound == false && iterator.hasNext()) {
			LogEntry currentEntry = iterator.next();
			if (currentEntry.getByteSent() == 0) {
				emptyFound = true;
			}
		}
		Assert.assertEquals(emptyFound, result);
	}
	
	@Test
	public void test6_wereThereRequestsAfter1995() {
		
		boolean result = service.wereThereRequestsAfter1995();
		
		List<LogEntry> logEntries = service.streamLogs().collect(Collectors.toList());
		boolean emptyFound = false;
		Iterator<LogEntry> iterator = logEntries.iterator();
		while (emptyFound == false && iterator.hasNext()) {
			LogEntry currentEntry = iterator.next();
			if (currentEntry.getDate().getYear() > 1995) {
				emptyFound = true;
			}
		}
		Assert.assertEquals(emptyFound, result);
	}
	

	@Test
	public void test7_wereThere() {
		
		boolean result = service.wereThere((le) -> le.getDate().getYear() > 1995);
		
		List<LogEntry> logEntries = service.streamLogs().collect(Collectors.toList());
		boolean emptyFound = false;
		Iterator<LogEntry> iterator = logEntries.iterator();
		while (emptyFound == false && iterator.hasNext()) {
			LogEntry currentEntry = iterator.next();
			if (currentEntry.getDate().getYear() > 1995) {
				emptyFound = true;
			}
		}
		Assert.assertEquals(emptyFound, result);
	}
	
	@Test
	public void test8_mapByDates() {
		Map<LocalDate, List<LogEntry>> map = service.mapByDates();
		Assert.assertNotNull(map);
	}
	
	@Test
	public void test9_findHowManyBytesSent() {
		Integer bytesSent = service.findHowManyBytesSent().get();
		
		List<LogEntry> logEntries = service.streamLogs().collect(Collectors.toList());
		Integer sum = 0;
		for (LogEntry entry : logEntries) {
			sum += entry.getByteSent();
		}
		Assert.assertEquals(sum, bytesSent);
	}
	
	@Test
	public void test10_howManyGets() {
		Long gets = service.HowManyGets();
		

		List<LogEntry> logEntries = service.streamLogs().collect(Collectors.toList());
		long count = 0;
		for (LogEntry entry : logEntries) {
			if (entry.getRequest().substring(0, entry.getRequest().indexOf(' ')).equals("GET")) {
				count++;
			}
		}
		Assert.assertEquals(count, gets.longValue());
	}
	
}
