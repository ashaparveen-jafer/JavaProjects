package com.jp.morgan.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jp.morgan.report.data.DataGeneration;
import com.jp.morgan.report.model.Instruction;
import com.jp.morgan.report.sort.AmountAndInstructionTypeComparator;

/**
 * Report Generation Class.
 * 
 * @author User
 *
 */
public class ReportGeneration {

	public List<Instruction> reportData = DataGeneration.populateDataList();

	public static final List<Instruction> finalReportData = new ArrayList<Instruction>();

	/**
	 * Constructor .
	 * 
	 */
	public ReportGeneration() {
		populateDynamicData();
	}

	/**
	 * Method to populate Instruction Model with 'AmountInUSD' and
	 * 'FinalSettlementDate' based on the rules given in requirement specification.
	 */
	public void populateDynamicData() {
		for (Instruction instruction : reportData) {
			double amount = instruction.getAgreedFx() * instruction.getUnits() * instruction.getPricePerUnit();
			instruction.setAmountInUSD(amount);
			Date settlementDate = instruction.getSettlementDate();
			String settlementDay = new SimpleDateFormat("EEE").format(settlementDate).toUpperCase();
			String currency = instruction.getCurrency();
			if (("SAT".equals(settlementDay) || "SUN".equals(settlementDay))
					&& !("AED".equals(currency) || "SAR".equals(currency))) {
				if ("SAT".equals(settlementDay)) {
					instruction.setFinalSettlementDate(DataGeneration.addDays(settlementDate, 2));
				} else if ("SUN".equals(settlementDay)) {
					instruction.setFinalSettlementDate(DataGeneration.addDays(settlementDate, 1));
				}
			} else if (("SAT".equals(settlementDay) || "FRI".equals(settlementDay))
					&& ("AED".equals(currency) || "SAR".equals(currency))) {
				if ("FRI".equals(settlementDay)) {
					instruction.setFinalSettlementDate(DataGeneration.addDays(settlementDate, 2));
				} else if ("SAT".equals(settlementDay)) {
					instruction.setFinalSettlementDate(DataGeneration.addDays(settlementDate, 1));
				}
			} else {
				instruction.setFinalSettlementDate(settlementDate);
			}
			finalReportData.add(instruction);
		}
	}

	/**
	 * Method to generate Report1
	 * 'Amount in USD settled incoming everyday'
	 */
	public void generateIncomingReport() {
		Map<Date, Double> incoming = new HashMap<Date, Double>();
		for (Instruction instruction : finalReportData) {
			if ("S".equals(instruction.getInstructionType())) {
				if (incoming.get(instruction.getFinalSettlementDate()) == null) {
					incoming.put(instruction.getFinalSettlementDate(), instruction.getAmountInUSD());
				} else {
					double totalAmount = incoming.get(instruction.getFinalSettlementDate())
							+ instruction.getAmountInUSD();
					incoming.put(instruction.getFinalSettlementDate(), new Double(totalAmount));
				}
			}

		}

		printReport(incoming, "INCOMING");
	}

	/**
	 * Method to generate Report2
	 * 'Amount in USD settled outgoing everyday'
	 */
	public void generateOutgoingReport() {
		Map<Date, Double> outgoing = new HashMap<Date, Double>();
		for (Instruction instruction : finalReportData) {
			if ("B".equals(instruction.getInstructionType())) {
				if (outgoing.get(instruction.getFinalSettlementDate()) == null) {
					outgoing.put(instruction.getFinalSettlementDate(), instruction.getAmountInUSD());
				} else {
					double totalAmount = outgoing.get(instruction.getFinalSettlementDate())
							+ instruction.getAmountInUSD();
					outgoing.put(instruction.getFinalSettlementDate(), new Double(totalAmount));
				}
			}

		}

		printReport(outgoing, "OUTGOING");
	}

	/**
	 * Method to generate Report3
	 * 'Ranking of entities based on incoming and outgoing amount'
	 */
	public void generateRankingReport() {
		generateRankingReportIncoming();
		generateRankingReportOutgoing();
	}

	/**
	 * to calculate ranking for incoming amount
	 */
	private void generateRankingReportIncoming() {
		List<Instruction> incomingList = new ArrayList<Instruction>();
		for (Instruction instruction : finalReportData) {
			if ("S".equals(instruction.getInstructionType())) {
				incomingList.add(instruction);
			}
		}
		Collections.sort(incomingList, new AmountAndInstructionTypeComparator());
		printRankReport(incomingList, "INCOMING");
	}

	/**
	 * To calculate ranking for outgoing amount.
	 */
	private void generateRankingReportOutgoing() {
		List<Instruction> outgoingList = new ArrayList<Instruction>();
		for (Instruction instruction : finalReportData) {
			if ("B".equals(instruction.getInstructionType())) {
				outgoingList.add(instruction);
			}
		}
		Collections.sort(outgoingList, new AmountAndInstructionTypeComparator());
		printRankReport(outgoingList, "OUTGOING");
	}

	/**
	 * To print the results.
	 * 
	 * @param rankReportList
	 * @param reportNameKey
	 */
	private void printRankReport(List<Instruction> rankReportList, String reportNameKey) {
		System.out.println("-----------------------------------------");
		System.out.println("Ranking of entities based " + reportNameKey + " Amount:");
		System.out.println("-----------------------------------------");
		System.out.println("Entity  ::  Rank");
		int index = 1;
		for (Instruction instruction : rankReportList) {
			System.out.println(instruction.getEntity() + "        " + index);
			index++;
		}
	}

	/**
	 * To print the results.
	 * 
	 * @param reportDetails
	 * @param reportNameKey
	 */
	private void printReport(Map<Date, Double> reportDetails, String reportNameKey) {
		Set<Date> keyset = reportDetails.keySet();
		System.out.println("-----------------------------------------");
		System.out.println("Amount in USD settled " + reportNameKey + " everyday:");
		System.out.println("-----------------------------------------");
		System.out.println("Date 	::  	Amount");
		for (Date date : keyset) {
			System.out.println(DataGeneration.generateDateString(date) + "         " + reportDetails.get(date));
		}
	}

}
