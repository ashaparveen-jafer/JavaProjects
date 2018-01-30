package com.jp.morgan.report.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.jp.morgan.report.model.Instruction;

/**
 * Utility class to generate data to populate the report.
 * 
 * @author User
 *
 */
public class DataGeneration {

	/**
	 * Method to populate the list of Instruction entity.
	 * 
	 * @return Instruction List
	 */
	public static List<Instruction> populateDataList() {
		List<Instruction> dataList = new ArrayList<Instruction>();
		dataList.add(populateInstruction("Foo", "B", 0.50, 200, 100.25, "SAR", generateDate("06 Jan 2016"),
				generateDate("08 Jan 2016"))); // SettlementDay in Friday [Holiday]
		dataList.add(populateInstruction("Boo", "B", 0.75, 20, 40.25, "IND", generateDate("07 Jan 2016"),
				generateDate("09 Jan 2016"))); // SettlementDay in saturday [Holiday ]
		dataList.add(populateInstruction("Doo", "B", 0.15, 350, 89.55, "SNG", generateDate("01 Jan 2016"),
				generateDate("04 Jan 2016")));
		dataList.add(populateInstruction("Soo", "B", 0.95, 100, 102.25, "MAL", generateDate("22 Jan 2016"),
				generateDate("25 Jan 2016")));
		dataList.add(populateInstruction("Bar", "S", 0.22, 10, 150.50, "AED", generateDate("20 Jan 2016"),
				generateDate("23 Jan 2016"))); // Settlement Day in Saturday [Holiday]
		dataList.add(populateInstruction("car", "S", 0.50, 200, 75.50, "INR", generateDate("05 Jan 2016"),
				generateDate("07 Jan 2016")));
		dataList.add(populateInstruction("Far", "S", 0.25, 150, 115.12, "EUR", generateDate("05 Jan 2016"),
				generateDate("06 Jan 2016")));
		return dataList;
	}

	/**
	 * Method to populate Instruction Model.
	 * @param entity
	 * @param instructionType
	 * @param agreedFx
	 * @param units
	 * @param pricePerUnit
	 * @param currency
	 * @param instructionDate
	 * @param settlementDate
	 * @return Instruction
	 */
	private static Instruction populateInstruction(String entity, String instructionType, double agreedFx, int units,
			double pricePerUnit, String currency, Date instructionDate, Date settlementDate) {
		Instruction instruction = new Instruction();
		instruction.setEntity(entity);
		instruction.setInstructionType(instructionType);
		instruction.setAgreedFx(agreedFx);
		instruction.setUnits(units);
		instruction.setPricePerUnit(pricePerUnit);
		instruction.setCurrency(currency);
		instruction.setInstructionDate(instructionDate);
		instruction.setSettlementDate(settlementDate);
		return instruction;
	}

	/**
	 * Utility Method to generate Date in specific format.
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date generateDate(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
		Date generatedDate = null;
		try {
			generatedDate = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return generatedDate;
	}

	/**
	 * Utility method to generate Date in String with specific format.
	 * 
	 * @param date
	 * @return String
	 */
	public static String generateDateString(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
		String generatedDate = null;
		generatedDate = simpleDateFormat.format(date);
		return generatedDate;
	}
	
	/**
	 * Utility method to add days to the specific date.
	 * 
	 * @param date
	 * @param days
	 * @return Date
	 */
	public static Date addDays(Date date, int days) {
		GregorianCalendar calender = new GregorianCalendar();
		calender.setTime(date);
		calender.add(Calendar.DATE, days);
		return calender.getTime();
	}

}
