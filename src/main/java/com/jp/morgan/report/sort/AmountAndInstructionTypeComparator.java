package com.jp.morgan.report.sort;

import java.util.Comparator;

import com.jp.morgan.report.model.Instruction;

/**
 * Comparator class for Instruction Entity.
 * 
 * @author User
 *
 */
public class AmountAndInstructionTypeComparator implements Comparator<Instruction>{

	public int compare(Instruction o1, Instruction o2) {
		return ((Double)o2.getAmountInUSD()).compareTo(((Double)o1.getAmountInUSD()));
	}

}
