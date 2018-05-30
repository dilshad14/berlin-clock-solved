package com.ubs.opsit.interviews;

import java.util.StringJoiner;
import com.ubs.opsit.interviews.TimeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeConverterImpl implements TimeConverter {
	 private static final Logger LOG = LoggerFactory.getLogger(TimeConverterImpl.class);
	
	public String convertTime(String aTime) {

		String [] timeElements = aTime.split(":");
		String [] clockTime = new String [5];
		clockTime[0] = getTopSecond(timeElements[2]);
		clockTime[1] = getTopHours(timeElements[0]);
		clockTime[2] = getBottomHours(timeElements[0]);
		clockTime[3] = getTopMinutes(timeElements[1]);
		clockTime[4] = getBottomMinutes(timeElements[1]);
		
		return prepareBerlinClockPattern(clockTime);
	}
	
	private String prepareBerlinClockPattern(String [] clockTime) {
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		for(int i=0;i<clockTime.length;i++) {
			// LOG.debug(clockTime[i]);
			sj.add(clockTime[i]);
		}
		System.out.println(sj.toString());
		return sj.toString();
	}
	
	private String getTopSecond(String sec) {
		int numSec = convertStringToInt(sec);
		if (numSec == 0 || numSec % 2 ==0) {
			return "Y";
		}else {
			return "O";
		}
		
	}
	private String getTopHours(String hour) {
		int numHr = convertStringToInt(hour);
		int unitHr = 5;
		int itr = numHr/unitHr;		
		return  getHourPattern( 4, unitHr, itr);
	}
	
	private String getBottomHours(String hour) {
		int numHr = convertStringToInt(hour);
		int unitHr = 5;
		int itr = numHr%unitHr;	// get the remaining hours	
		return  getHourPattern(4, unitHr, itr);
	}
	
	private String getHourPattern(int patternLength,int unitHr,int iteration) {
		
		StringBuilder pattern = new StringBuilder("");		
		for(int i=1;i<=patternLength;i++) {
			
			if(i <= iteration) {		
				pattern.append("R");
		}else {
			pattern.append("O");
		}
		}
		return pattern.toString();
	}
	


	private String getTopMinutes(String min) {
		
		int numMin = convertStringToInt(min);
		int unitMin = 5;
		int itr = numMin/unitMin;		
		return  getMinutePattern(11, unitMin, itr);
		
	}
	private String getBottomMinutes(String min) {
		int numMin = convertStringToInt(min);
		int unitMin = 5;
		int itr = numMin%unitMin; // get remaining minutes		
		return  getMinutePattern(4, unitMin, itr);
	}

	private String getMinutePattern(int patternLength,int unitMin,int iteration) {
		
		StringBuilder pattern = new StringBuilder("");		
		for(int i=1;i<=patternLength;i++) {
			
			if(i <= iteration) {		
				//pattern.append("Y");
				if (patternLength == 11) {
					// its top minute row
					pattern.append(getTopMinFlag(i));}
				else {
					// its bottom minute row
					pattern.append("Y");
				}
		}else {
			pattern.append("O");
		}
		}
		return pattern.toString();
	}
	
	private String getTopMinFlag(int index) {{
		if (index ==3 || index ==6 || index==9) {
			return "R";
		}else {
			return "Y";
		}
	}
		
	}
	
	private int convertStringToInt(String num) {
		return Integer.parseInt(num);
	}
}
