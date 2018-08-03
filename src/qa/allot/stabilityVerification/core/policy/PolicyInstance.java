package qa.allot.stabilityVerification.core.policy;

import java.util.List;

import qa.allot.stabilityVerification.core.trafficStreams.TrafficStream;

/**
 * Policy instance class contain all required policy attributes (Line/pipe/VC  names , BW , CER ..)
 *  + helpers ( jumbo , QoS , DoS ... )
 *    
 * @author vbronshtein
 *
 */
public class PolicyInstance {
	private String lineName;
	private String pipeName;
	private String vcName;
	private List<String> trafficStreamNames;

	//helper attributes
	private boolean isShowOnFinalCsv;
	private boolean qos;
	private boolean dos;
	private boolean jumbo;
	private boolean bypass;

	private int bw = 0;
	private int cer = 0;
	private int liveConnections = 0;
	private int dropConnections = 0;

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getPipeName() {
		return pipeName;
	}

	public void setPipeName(String pipeName) {
		this.pipeName = pipeName;
	}

	public String getVcName() {
		return vcName;
	}

	public void setVcName(String vcName) {
		this.vcName = vcName;
	}

	public List<String> getTrafficStreamNames() {
		return trafficStreamNames;
	}

	public void setTrafficStreamNames(List<String> trafficStreamNames) {
		this.trafficStreamNames = trafficStreamNames;
	}

	public boolean isQos() {
		return qos;
	}

	public void setQos(boolean qos) {
		this.qos = qos;
	}

	public boolean isDos() {
		return dos;
	}

	public void setDos(boolean dos) {
		this.dos = dos;
	}

	public boolean isJumbo() {
		return jumbo;
	}

	public void setJumbo(boolean jumbo) {
		this.jumbo = jumbo;
	}

	public int getBw() {
		return bw;
	}

	public void setBw(int bw) {
		this.bw = bw;
	}

	public int getCer() {
		return cer;
	}

	public void setCer(int cer) {
		this.cer = cer;
	}

	public int getLiveConnections() {
		return liveConnections;
	}

	public void setLiveConnections(int liveConnections) {
		this.liveConnections = liveConnections;
	}

	public int getDropConnections() {
		return dropConnections;
	}

	public void setDropConnections(int dropConnections) {
		this.dropConnections = dropConnections;
	}

	public boolean isBypass() {
		return bypass;
	}

	public void setBypass(boolean bypass) {
		this.bypass = bypass;
	}

	public boolean isShowOnFinalCsv() {
		return isShowOnFinalCsv;
	}

	public void setShowOnFinalCsv(boolean isShowOnFinalCsv) {
		this.isShowOnFinalCsv = isShowOnFinalCsv;
	}

	@Override
	public String toString() {
		return "PolicyInstance [lineName=" + lineName + ", pipeName=" + pipeName + ", vcName=" + vcName
				+ ", isShowOnFinalCsv=" + isShowOnFinalCsv + ", qos=" + qos + ", dos=" + dos + ", jumbo=" + jumbo
				+ ", bypass=" + bypass + ", bw=" + bw + ", cer=" + cer + ", liveConnections=" + liveConnections
				+ ", dropConnections=" + dropConnections + "]";
	}

}
