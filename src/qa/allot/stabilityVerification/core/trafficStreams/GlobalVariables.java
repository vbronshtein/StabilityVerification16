package qa.allot.stabilityVerification.core.trafficStreams;

import java.util.List;

/**
 * Class calculate Global Variable tables : 
 * # of links
 * Links capacitiy (Mbps)
 * port utilization % 
 * Average frame size (Byte) 
 * IFG+Preamble (Byte) 
 * FPS 
 * FPS per stream
 * 
 * @author vbronshtein
 *
 */
public class GlobalVariables {
	private int numberOfLinks;
	private int linkCapacity;
	private int portUtilizationPersent;

	private float AvgFrameSize;
	private static final int IFG_AND_PRIAMBLE = 20;
	private float fps;
	private int fpsPerStream;
	private List<TrafficStream> streams;

	//CTOR
	public GlobalVariables(int numberOfLinks, int linkCapacity, int portUtilizationPersent,
			List<TrafficStream> streams) {
		super();
		this.numberOfLinks = numberOfLinks;
		this.linkCapacity = linkCapacity;
		this.portUtilizationPersent = portUtilizationPersent;
		this.streams = streams;
		fpsCalculation();
		fpsPerStreamCalc();
	}

	@Override
	public String toString() {
		return "GlobalVariables [numberOfLinks=" + numberOfLinks + ", linkCapacity=" + linkCapacity
				+ ", portUtilizationPersent=" + portUtilizationPersent + ", AvgFrameSize=" + AvgFrameSize + ", fps="
				+ fps + ", fpsPerStream=" + fpsPerStream + "]";
	}

	public int getNumberOfLinks() {
		return numberOfLinks;
	}

	public void setNumberOfLinks(int numberOfLinks) {
		this.numberOfLinks = numberOfLinks;
	}

	public int getLinkCapacity() {
		return linkCapacity;
	}

	public void setLinkCapacity(int linkCapacity) {
		this.linkCapacity = linkCapacity;
	}

	public int getPortUtilizationPersent() {
		return portUtilizationPersent;
	}

	public void setPortUtilizationPersent(int portUtilizationPersent) {
		this.portUtilizationPersent = portUtilizationPersent;
	}

	public List<TrafficStream> getStreams() {
		return streams;
	}

	public void setStreams(List<TrafficStream> streams) {
		this.streams = streams;
	}

	public float getAvgFrameSize() {
		return AvgFrameSize;
	}

	public float getFps() {
		return fps;
	}

	public int getFpsPerStream() {
		return fpsPerStream;
	}

	public float avgFrameSizeCalc() {
		float sum = 0;
		for (TrafficStream stream : streams) {
			sum += stream.getFrameLength();
		}
		this.AvgFrameSize = sum / streams.size();
		return this.AvgFrameSize;
	}

	public void fpsCalculation() {
		fps = (int) ((linkCapacity * 1_000_000L * portUtilizationPersent / 100f)
				/ (long) ((avgFrameSizeCalc() + IFG_AND_PRIAMBLE) * 8));
	}

	//fps per stream calculation
	public void fpsPerStreamCalc() {
		fpsPerStream = (int) ((fps * 2) / streams.size());
	}

}
