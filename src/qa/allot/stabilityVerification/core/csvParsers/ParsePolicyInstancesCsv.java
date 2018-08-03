package qa.allot.stabilityVerification.core.csvParsers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import qa.allot.stabilityVerification.core.policy.LineInstance;
import qa.allot.stabilityVerification.core.policy.PipeInstance;
import qa.allot.stabilityVerification.core.policy.PolicyInstance;
import qa.allot.stabilityVerification.core.policy.VcInstance;

/**
 * Parse Traffic instances from external CSV into TrafficStream instances
 * 
 * @author vbronshtein
 *
 */
public class ParsePolicyInstancesCsv {
	
	/**
	 * The "readVcFromCsv" method parse "ffiles/PolicyInstances.csv" into List of PolicyInstance Objects
	 * 
	 * @return
	 */
	public List<PolicyInstance> readVcFromCsv() {
		String expectedCsvFile = "files/PolicyInstances.csv";
		String line = "";
		String cvsSplitBy = ",";
		List<PolicyInstance> policyInstances = new ArrayList<>();
		boolean isFirstLine = true;
		
		//parse "files/PolicyInstances.csv"
		try (BufferedReader br = new BufferedReader(new FileReader(expectedCsvFile))) {

			//read line
			while ((line = br.readLine()) != null) {
				String[] csvStrem = line.split(cvsSplitBy);

				//if first line continue to  read next line ( header line not interested here ) 
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}

				//if not first line create Policy instances [Line][Pipe][VC]
				//VC case
				if (!csvStrem[3].equals("")) {

					VcInstance vcInstance = new VcInstance();
					vcInstance.setVcName(csvStrem[3]);
					vcInstance.setPipeName(csvStrem[2]);
					vcInstance.setLineName(csvStrem[1]);

					//read traffic streams of current VC instance ( tabs 7 -27 ) from csv 
					int firstTrafficStreamIndex = 7;
					int lastTrafficStreamIndex = 27;
					int currIndex = firstTrafficStreamIndex;
					List<String> trafficStreams = new ArrayList<>();
					while (currIndex < lastTrafficStreamIndex && !csvStrem[currIndex].equals("")) {
						trafficStreams.add(csvStrem[currIndex]);
						currIndex++;

					}
					vcInstance.setTrafficStreamNames(trafficStreams);
					
					//read all helper parameters
					vcInstance.setShowOnFinalCsv(Integer.parseInt(csvStrem[0]) > 0 ? true : false);
					vcInstance.setQos(Integer.parseInt(csvStrem[4]) > 0 ? true : false);
					vcInstance.setDos(Integer.parseInt(csvStrem[5]) > 0 ? true : false);
					vcInstance.setJumbo(Integer.parseInt(csvStrem[6]) > 0 ? true : false);
					vcInstance.setBypass(Integer.parseInt(csvStrem[7]) > 0 ? true : false);
					
					//add instance to the PolicyIncence Collection
					policyInstances.add(vcInstance);
					
				//pipe case
				} else if (!csvStrem[2].equals("")) {
					//create pipeInstance with Line/Pipe names
					PipeInstance pipeInstance = new PipeInstance();
					pipeInstance.setPipeName(csvStrem[2]);
					pipeInstance.setLineName(csvStrem[1]);
					pipeInstance.setShowOnFinalCsv(Integer.parseInt(csvStrem[0]) > 0 ? true : false);
					
					//add instance to the PolicyIncence Collection
					policyInstances.add(pipeInstance);
				
				//Line case 
				} else if (!csvStrem[1].equals("")) {
					//create lineInstance with Line name
					LineInstance lineInstance = new LineInstance();
					lineInstance.setLineName(csvStrem[1]);
					lineInstance.setShowOnFinalCsv(Integer.parseInt(csvStrem[0]) > 0 ? true : false);
					
					//add instance to the PolicyIncence Collection
					policyInstances.add(lineInstance);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		//return final PolicyIncence Collection
		return policyInstances;
	}

}
