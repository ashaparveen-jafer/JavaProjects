package com.jp.morgan.report.model;

import java.util.Date;

/**
 * Model class to hold report details.
 * 
 * @author User
 *
 */
public class Instruction {
	
	private String entity;
	
	private String instructionType;
	
	private double agreedFx;
	
	private String currency;
	
	private Date instructionDate;
	
	private Date settlementDate;
	
	private int units;
	
	private double pricePerUnit;
	
	private double amountInUSD;
	
	private Date finalSettlementDate;
	
	public Date getFinalSettlementDate() {
		return finalSettlementDate;
	}

	public void setFinalSettlementDate(Date finalSettlementDate) {
		this.finalSettlementDate = finalSettlementDate;
	}

	public double getAgreedFx() {
		return agreedFx;
	}

	public void setAgreedFx(double agreedFx) {
		this.agreedFx = agreedFx;
	}

	public double getAmountInUSD() {
		return amountInUSD;
	}

	public void setAmountInUSD(double amountInUSD) {
		this.amountInUSD = amountInUSD;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getInstructionType() {
		return instructionType;
	}

	public void setInstructionType(String instructionType) {
		this.instructionType = instructionType;
	}


	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	
	@Override
	public String toString() {
		return "Entity:: "+ entity + ", InstructionType:: "+ instructionType +", AgreedFx:: "+ agreedFx +", currency::" + currency +
				", InstructionDate:: "+ instructionDate +", SettlementDate:: "+ settlementDate +", units:: "+ units +
				", PriceperUnit :: " + pricePerUnit + ", AmountInUSD:: "+ amountInUSD +", Final Settlement Date :: "+ finalSettlementDate;
	}

}
