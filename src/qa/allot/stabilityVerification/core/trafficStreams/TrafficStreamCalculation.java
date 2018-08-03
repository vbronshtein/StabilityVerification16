package qa.allot.stabilityVerification.core.trafficStreams;

import java.util.List;

public class TrafficStreamCalculation {

	/**
	 * Calculate traffic stream variables ( BW/NOC/CER )
	 * 
	 * @param streams
	 * @param numOfLinks
	 * @param fpsPerStream
	 */
	public void calculateBwNocCer(List<TrafficStream> streams, int numOfLinks, int fpsPerStream) {
		bwCalculation(streams, numOfLinks, fpsPerStream);
		nocCalculation(streams, numOfLinks, fpsPerStream);
		cerCalculation(streams, numOfLinks, fpsPerStream);
	}

	/**
	 * 
	 * Method calculate BW of For all Traffic Streams
	 * 
	 * @param streams
	 *            - List of Traffic Streams
	 * @param numOfLinks
	 * @param fpsPerStream
	 */
	private void bwCalculation(List<TrafficStream> streams, int numOfLinks, int fpsPerStream) {
		for (TrafficStream stream : streams) {
			stream.setBw((long)numOfLinks * fpsPerStream * stream.getFrameLength() * 8 / 1000);
		}

	}

	/**
	 * 
	 * Method calculate NOC of For all Traffic Streams
	 * 
	 * @param streams
	 *            - List of Traffic Streams
	 * @param numOfLinks
	 * @param fpsPerStream
	 */
	private void nocCalculation(List<TrafficStream> streams, int numOfLinks, int fpsPerStream) {
		for (TrafficStream stream : streams) {
			if ((fpsPerStream * stream.getTimeOut()) < stream.getModifierCount()) {
				stream.setNoc(fpsPerStream * stream.getTimeOut());
			} else {
				stream.setNoc(stream.getModifierCount());
			}
		}

	}

	/**
	 * 
	 * Method calculate CER of For all Traffic Streams
	 * 
	 * @param streams
	 * @param numOfLinks
	 * @param fpsPerStream
	 */
	private void cerCalculation(List<TrafficStream> streams, int numOfLinks, int fpsPerStream) {
		for (TrafficStream stream : streams) {
			if (stream.getNoc() < stream.getModifierCount()) {
				stream.setCer(fpsPerStream);
			} else {
				stream.setCer(0);
			}
		}
	}

}
