package qa.allot.stabilityVerification.core.trafficStreams;

public class TrafficStream {
	private String name;
	private int frameLength;
	private int modifierCount;
	private int timeOut;
	private boolean isInternalStream;
	

	private float bw;
	private int cer;
	private int noc;
	
	//CTOR
	public TrafficStream() {
		super();
	}

	//Geters and Seters
	public String getName() {
		return name;
	} 
	
	/**
	 * 
	 * @param name
	 * @param frameLength
	 * @param modifierCount
	 * @param timeOut
	 */
	public TrafficStream(String name, int frameLength, int modifierCount, int timeOut) {
		super();
		this.name = name;
		this.frameLength = frameLength;
		this.modifierCount = modifierCount;
		this.timeOut = timeOut;
	}
	

	public void setName(String name) {
		this.name = name;
	}

	public int getFrameLength() {
		return frameLength;
	}

	public void setFrameLength(int frameLength) {
		this.frameLength = frameLength;
	}

	public int getModifierCount() {
		return modifierCount;
	}

	public void setModifierCount(int modifierCount) {
		this.modifierCount = modifierCount;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public float getBw() {
		return bw;
	}

	public void setBw(float bw) {
		this.bw = bw;
	}

	public int getCer() {
		return cer;
	}

	public void setCer(int cer) {
		this.cer = cer;
	}

	public int getNoc() {
		return noc;
	}

	public void setNoc(int noc) {
		this.noc = noc;
	}
	
	

	public boolean isInternalStream() {
		return isInternalStream;
	}

	public void setInternalStream(boolean isInternalStream) {
		this.isInternalStream = isInternalStream;
	}

	@Override
	public String toString() {
		return "TrafficStream [name=" + name + ", frameLength=" + frameLength + ", modifierCount=" + modifierCount
				+ ", timeOut=" + timeOut + ", bw=" + bw + ", cer=" + cer + ", noc=" + noc + "]";
	}
	
	

}
