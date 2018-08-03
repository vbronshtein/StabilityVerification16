package main;

import java.util.List;

import qa.allot.stabilityVerification.core.csvParsers.GenerateFinalCsv;
import qa.allot.stabilityVerification.core.csvParsers.ParsePolicyInstancesCsv;
import qa.allot.stabilityVerification.core.csvParsers.ParseTrafficCsvToTrafficStreams;
import qa.allot.stabilityVerification.core.helpers.PolicyInstanceCalculation;
import qa.allot.stabilityVerification.core.helpers.PolicyInstanceClassification;
import qa.allot.stabilityVerification.core.policy.PolicyInstance;
import qa.allot.stabilityVerification.core.trafficStreams.GlobalVariables;
import qa.allot.stabilityVerification.core.trafficStreams.QosTable;
import qa.allot.stabilityVerification.core.trafficStreams.TrafficStream;
import qa.allot.stabilityVerification.core.trafficStreams.TrafficStreamCalculation;

/**
 * Stability Verification tool 
 * 
 * Based on Stability_reference.xls calculations
 * 
 * For adding new Traffic stream add Stream parameters to /files/TrafficStreams.csv
 * For adding new Policy Instance add policy names to /files/PolicyInstances.csv
 * 
 * For Disable traffic streams : update Enable_Stream tab on /files/TrafficStreams.csv
 * For Disable policy instance : update Enable_Stream tab on /files/ShowOnFinalCsv.csv
 * 
 * @author vbronshtein
 *
 */
public class StabilityVerificationProgram {
	public static void main(String[] args) {

//		 int numOfLinks = 5;
//		 int portSpeed = 10_000;
//		 int portUtilizationPercent = 50;
//		 boolean jumboEnable = true;

		 int numOfLinks = Integer.parseInt(args[0]);
		 int portSpeed = Integer.parseInt(args[1]);
		 int portUtilizationPercent = Integer.parseInt(args[2]);
		 boolean jumboEnable = (Integer.parseInt(args[3]) > 0 ? true : false);
		
		 
		/**
		 * Traffic streams parsing and calculation
		 */
		System.out.println("---------start---------");
		//Parsing traffic streams from csv
		ParseTrafficCsvToTrafficStreams parse = new ParseTrafficCsvToTrafficStreams(); // need to be a singleton
		List<TrafficStream> streams = parse.readCsv();
		
		System.out.println("---------Generate global Variables---------");
		//Calculation of Global variables
		GlobalVariables globalVariables = new GlobalVariables(numOfLinks, portSpeed, portUtilizationPercent, streams);
		System.out.println("FpsPerStream : "+globalVariables.getFpsPerStream());
		
		//Calculation of BW/CER/NOC per traffic stream
		System.out.println("---------Start calculation---------");
		TrafficStreamCalculation trafficStreamCalculation = new TrafficStreamCalculation();
		trafficStreamCalculation.calculateBwNocCer(streams, numOfLinks, globalVariables.getFpsPerStream());
		
		

		/**
		 * Calculate Expected valuef for Policy instances
		 */
		//Parse Policy inctances into PolicyInstance object
		ParsePolicyInstancesCsv parseEcpected = new ParsePolicyInstancesCsv();
		List<PolicyInstance> policyInstances = parseEcpected.readVcFromCsv();

		//Classification of VCs instances to Pipes / Pips to Lines
		PolicyInstanceClassification policyInstanceClassification = new PolicyInstanceClassification();
		policyInstanceClassification.poliClasification(policyInstances);
		
		
		
		//Calculate QoS table
		QosTable qosTable = new QosTable();
		qosTable.stcQosCalculation(streams);
		qosTable.print();
		
		
		//Calculate policy instances
		PolicyInstanceCalculation policyInstanceCalculation = new PolicyInstanceCalculation(policyInstances);
		policyInstanceCalculation.CalculatePolicyValues(streams ,qosTable ,jumboEnable);

		//Generate Final Csv
		System.out.println("---------Generate Final csv---------");
		GenerateFinalCsv finalCsv = new GenerateFinalCsv();
		finalCsv.createResultCsv(policyInstances);
		System.out.println("File saved in : " + finalCsv.getCsvoutFile());
		System.out.println("---------done---------");
	}
}
