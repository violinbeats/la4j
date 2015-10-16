package fit.date;

import date.Date;
import date.InvalidDateException;
import fit.ColumnFixture;

public class NextDate extends ColumnFixture{
	public int day;
	public int month;
	public int year;
	
	public String nextDate() throws InvalidDateException{
		if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1900 || year > 2200 ){
			return null;
		}
		
		String d = Integer.toString(day+1);
		String m = Integer.toString(month);
		String y = Integer.toString(year);
		String dot = ".";
		return d.concat(dot).concat(m).concat(dot).concat(y);
	}
	
	public boolean isLeapYear() throws InvalidDateException{
		Date d = new Date(day, month, year);
		return d.isLeapYear();
	}
}
