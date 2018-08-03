package qa.allot.stabilityVerification.core.csvParsers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import qa.allot.stabilityVerification.core.trafficStreams.TrafficStream;

/**
 * Class of Parsing Traffic streams from Csv into Traffic stream objects
 * 
 * @author vbronshtein
 *
 */
public class ParseTrafficCsvToTrafficStreams {

	/**
	 * The "readCsv()" method parse "files/TrafficStreams.csv" into List of TrafficStreams Objects
	 * 
	 * 
	 * @return
	 */
	public List<TrafficStream> readCsv() {
		String csvFile = "files/TrafficStreams.csv";
		String line = "";
		String cvsSplitBy = ",";
		List<TrafficStream> streams = new ArrayList<>();
		boolean isFirstLine = true;

		//parse "files/TrafficStreams.csv"
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			//read line
			while ((line = br.readLine()) != null) {
				String[] csvStrem = line.split(cvsSplitBy);

				// if first line continue to read next line ( header line not interested here )
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				
				// if  the stream Status is Enable continue to next line
				if (Integer.parseInt(csvStrem[0]) == 0) {
					continue;
				}
				
				//Otherwice create trafficStream instance
				TrafficStream stream = new TrafficStream();
				stream.setName(csvStrem[1]);
				stream.setInternalStream(Integer.parseInt(csvStrem[4]) > 0 ? true : false);
				stream.setFrameLength(Integer.parseInt(csvStrem[7]));
				stream.setModifierCount(Integer.parseInt(csvStrem[8]));
				stream.setTimeOut(Integer.parseInt(csvStrem[9]));
				// add traffic stream to Traffic Stream Collection
				streams.add(stream);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		//return final collection with all traffic streams
		return streams;
	}

}
