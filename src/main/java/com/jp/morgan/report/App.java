package com.jp.morgan.report;

/**
 * Main Class
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ReportGeneration reportGeneration = new ReportGeneration();
        reportGeneration.generateIncomingReport();
        reportGeneration.generateOutgoingReport();
        reportGeneration.generateRankingReport();
    }
}
