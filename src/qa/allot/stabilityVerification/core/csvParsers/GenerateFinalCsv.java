package qa.allot.stabilityVerification.core.csvParsers;

import java.beans.VetoableChangeListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import qa.allot.stabilityVerification.core.policy.PipeInstance;
import qa.allot.stabilityVerification.core.policy.PolicyInstance;
import qa.allot.stabilityVerification.core.policy.VcInstance;

/**
 * The "GenerateFinalCsv" class generate Final CSVs from PolicyInstances
 * 
 * @author vbronshtein
 *
 */
public class GenerateFinalCsv {

	private String csvoutFile = "outputfiles/StabilityValidation.csv";
	private String line = "\n";
	private String cvsSplitBy = ",";

	public String getCsvoutFile() {
		return csvoutFile;
	}

	public void setCsvoutFile(String csvoutFile) {
		this.csvoutFile = csvoutFile;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getCvsSplitBy() {
		return cvsSplitBy;
	}

	public void setCvsSplitBy(String cvsSplitBy) {
		this.cvsSplitBy = cvsSplitBy;
	}

	
	/**
	 * createResultCs Method Generate final CSV from policy instances
	 * 
	 * 
	 * @param instances
	 */
	public void createResultCsv(List<PolicyInstance> instances) {
		boolean isFirstLine = true;

		try (BufferedWriter out = new BufferedWriter(new FileWriter(csvoutFile));) {
			// If first line add Headlines
			if (isFirstLine) {
				out.write("Line_Name" + cvsSplitBy);
				out.write("Pipe_Name" + cvsSplitBy);
				out.write("VC_Name" + cvsSplitBy);
				out.write("BW_kbps" + cvsSplitBy);
				out.write("CER" + cvsSplitBy);
				out.write("Live_Connections" + cvsSplitBy);
				out.write("Drop_Connections" + cvsSplitBy);
				out.write(line);
				isFirstLine = false;
			}
			// Generate CSVs records per each instance
			for (PolicyInstance policyInstance : instances) {
				// if ShowOnFinalCsv disable move to next policy instance
				if (!policyInstance.isShowOnFinalCsv()) {
					continue;
				}
				// write Line/Pipe/VC names
				out.write(policyInstance.getLineName() + cvsSplitBy);
				if (policyInstance.getPipeName() != null) {
					out.write(policyInstance.getPipeName() + cvsSplitBy);
				} else {
					out.write(cvsSplitBy);
				}
				if (policyInstance.getVcName() != null) {
					out.write(policyInstance.getVcName() + cvsSplitBy);
				} else {
					out.write(cvsSplitBy);
				}
				// write calculated values BW/CER/NOC ..
				out.write(policyInstance.getBw() + cvsSplitBy);
				out.write(policyInstance.getCer() + cvsSplitBy);
				out.write(policyInstance.getLiveConnections() + cvsSplitBy);
				out.write(policyInstance.getDropConnections() + cvsSplitBy);
				out.write(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
