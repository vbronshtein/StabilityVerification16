package qa.allot.stabilityVerification.core.csvParsers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import qa.allot.stabilityVerification.core.trafficStreams.QosTable;

/**
 * The "GenerateQoSCsv" class generate csv with Qos catalogs for stability test 
 * 
 * @author vbronshtein
 *
 */
public class GenerateQoSCsv {

	private String csvOutFile = "outputfiles/QoSCatalogs.csv";
	private String line = "\n";
	private String cvsSplitBy = ",";
	
	
	

	public String getCsvOutFile() {
		return csvOutFile;
	}




	public void setCsvOutFile(String csvOutFile) {
		this.csvOutFile = csvOutFile;
	}




	/**
	 * createQoSCsv method generate csv with qos values for stability test :
	 * 
	 * Pipe_Max_Qos
	 * vc_Qos_Mb_Pri1
	 * vc_Qos_Mb_Pri2
	 * vc_Qos_Mb_Pri3
	 * vc_Qos_Mb_Pri4
	 * 
	 * @param qosTable
	 */
	public void createQoSCsv(QosTable qosTable) {
		boolean isFirstLine = true;

		try (BufferedWriter out = new BufferedWriter(new FileWriter(csvOutFile));) {
			// If first line add Headlines
			if (isFirstLine) {
				out.write("QoS_Catalog_name" + cvsSplitBy);
				out.write("QoS_Value" + cvsSplitBy);
				out.write(line);
				isFirstLine = false;
			}
			// Generate CSVs records per each instance
			out.write("Pipe_Max_Qos" + cvsSplitBy);
			out.write(qosTable.getPipeMaxQos() + cvsSplitBy);
			out.write(line);

			out.write("vc_Qos_Mb_Pri1" + cvsSplitBy);
			out.write(qosTable.getVcCatalogQosMbPri1() + cvsSplitBy);
			out.write(line);
			
			out.write("vc_Qos_Mb_Pri2" + cvsSplitBy);
			out.write(qosTable.getVcCatalogQosMbPri2() + cvsSplitBy);
			out.write(line);
			
			out.write("vc_Qos_Mb_Pri3" + cvsSplitBy);
			out.write(qosTable.getVcCatalogQosMbPri3() + cvsSplitBy);
			out.write(line);
			
			out.write("vc_Qos_Mb_Pri4" + cvsSplitBy);
			out.write(qosTable.getVcCatalogQosMbPri4() + cvsSplitBy);
			out.write(line);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}






